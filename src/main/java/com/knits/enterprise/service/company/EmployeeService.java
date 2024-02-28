package com.knits.enterprise.service.company;


import com.knits.enterprise.dto.analytics.*;
import com.knits.enterprise.dto.company.EmployeeDto;
import com.knits.enterprise.dto.search.EmployeeSearchDto;
import com.knits.enterprise.exception.UserException;
import com.knits.enterprise.mapper.company.BusinessUnitMapper;
import com.knits.enterprise.mapper.company.DepartmentMapper;
import com.knits.enterprise.mapper.company.EmployeeMapper;
import com.knits.enterprise.mapper.company.JobTitleMapper;
import com.knits.enterprise.mapper.location.LocationMapper;
import com.knits.enterprise.model.company.Employee;
import com.knits.enterprise.repository.company.EmployeeRepository;
import com.knits.enterprise.util.excel.company.EmployeeExelUtil;
import com.knits.enterprise.repository.projection.AgeGroupCountView;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static com.knits.enterprise.util.excel.company.EmployeeExelUtil.createByteOutputStreamFromWorkbook;
import static com.knits.enterprise.util.excel.company.EmployeeExelUtil.findFileStream;


@Service
@Slf4j
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeMapper employeeMapper;

    private final BusinessUnitMapper businessUnitMapper;

    private final JobTitleMapper jobTitleMapper;

    private final DepartmentMapper departmentMapper;

    private final LocationMapper locationMapper;
    private final EmployeeRepository employeeRepository;
    @Transactional
    public EmployeeDto saveNewEmployee(EmployeeDto employeeDto) {
        Employee employee = employeeMapper.toEntity(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return employeeMapper.toDto(savedEmployee);
    }
    @Transactional(readOnly = true)
    public EmployeeDto findEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new UserException("User#" + id + " not found"));
        return employeeMapper.toDto(employee);
    }
    @Transactional
    public EmployeeDto partialUpdate(EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findById(employeeDto.getId()).orElseThrow(() -> new UserException("User#" + employeeDto.getId() + " not found"));

        employeeMapper.partialUpdate(employee, employeeDto);
        employeeRepository.save(employee);
        return employeeMapper.toDto(employee);
    }
    @Transactional
    public EmployeeDto deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id).get();
        employeeRepository.delete(employee);
        return employeeMapper.toDto(employee);

    }
    @Transactional
    public PageImpl<EmployeeDto> searchForEmployees(EmployeeSearchDto searchDto) {
        Page<Employee> foundEmployees = employeeRepository.findAll(searchDto.getSpecification(), searchDto.getPageable());
        List<EmployeeDto> listEmployeesDtos = employeeMapper.toDtos(foundEmployees.getContent());
        return new PageImpl<>(listEmployeesDtos, searchDto.getPageable(), foundEmployees.getTotalElements());
    }
    @Transactional
    public ByteArrayOutputStream makeExelOfEmployees(EmployeeSearchDto searchDto) {
        Page<Employee> foundEmployees = employeeRepository.findAll(searchDto.getSpecification(), searchDto.getPageable());
        List<EmployeeDto> listEmployeesDtos = employeeMapper.toDtos(foundEmployees.getContent());
        Workbook wb = new HSSFWorkbook();
        CreationHelper creationHelper = wb.getCreationHelper();
        Sheet sheet = wb.createSheet("main");
        if (listEmployeesDtos.isEmpty()) return null;
        EmployeeExelUtil.fillExcelTableWithEmployeesData(sheet, listEmployeesDtos, creationHelper);
        return createByteOutputStreamFromWorkbook(wb);
    }
    @Transactional
    public void addEmployeeFromExelFile(String fileName) {
        List<Employee> employees = new ArrayList<>();
        try {
            InputStream fis = findFileStream(fileName);
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0); // Assuming the data is in the first sheet

            Iterator<Row> rowIterator = sheet.iterator();

            // Skip the header row (if present)
            if (rowIterator.hasNext()) {
                rowIterator.next(); // Skip the header row
            }
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Employee employee = EmployeeExelUtil.createEmployeeFromRow(row);
                employees.add(employee);
            }
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        employeeRepository.saveAll(employees);
    }
    @Transactional
    public EmployeeAnalyticsDto employeeAnalytics() {
        List<AgeGroupCountView> ageGroupCountViews = employeeRepository.countEmployeesByAgeGroup();

        List<EmployeesCountByAgeGroupDto> employeesCountByAgeGroupDtos = ageGroupCountViews.stream().map(view -> {
            return new EmployeesCountByAgeGroupDto(view.getAgeGroup(), view.getEmployeesCount());
        }).collect(Collectors.toList());

        List<EmployeesCountByGenderDto> employeesCountByGenderDtos = employeeRepository.countEmployeesByGender();

        List<EmployeesCountByOfficeDto> employeesCountByOfficesDtos = employeeRepository.countEmployeesByOffice().stream().map(projection -> {
            return new EmployeesCountByOfficeDto(locationMapper.toDto(projection.getOffice()), projection.getEmployeesCount());
        }).collect(Collectors.toList());

        List<EmployeesCountByBusinessUnitDto> employeesCountByBusinessUnitsDtos = employeeRepository.countEmployeesByBusinessUnit().stream().map(projection -> {
            return new EmployeesCountByBusinessUnitDto(businessUnitMapper.toDto(projection.getBusinessUnit()), projection.getEmployeesCount());
        }).collect(Collectors.toList());

        List<EmployeesCountByJobTitleDto> employeesCountByJobTitlesDtos = employeeRepository.countEmployeesByJobTitle().stream().map(projection -> {
            return new EmployeesCountByJobTitleDto(jobTitleMapper.toDto(projection.getJobTitle()), projection.getEmployeesCount());
        }).collect(Collectors.toList());

        List<EmployeesCountInDepartmentDto> employeesCountByDepartmentsDtos = employeeRepository.countEmployeesByDepartment().stream().map(projection -> {
            return new EmployeesCountInDepartmentDto(departmentMapper.toDto(projection.getDepartment()), projection.getEmployeesCount());
        }).collect(Collectors.toList());

        List<EmployeesCountByExperienceDto> employeesCountByExperienceDtos = employeeRepository.countEmployeesByExperience().stream().map(view -> {
            return new EmployeesCountByExperienceDto(view.getExperienceGroup(), view.getEmployeesCount());
        }).collect(Collectors.toList());
        List<EmployeesHiredCountByYearDto> employeesHiredCountByYearDtos = employeeRepository.countHiredEmployeesByYear();

        List<EmployeesLeftCountByYearDto> employeesLeftCountByYearDtos = employeeRepository.countLeftEmployeesByYear();

        List<EmployeesTotalCountByYearDto> employeesTotalCountByYears = new ArrayList<>();
        makeListOfTotalEmployeesCountPerYear(employeesTotalCountByYears, employeesHiredCountByYearDtos, employeesLeftCountByYearDtos);

        EmployeesCountInBusinessUnitDto bestEmployeesCountInBusinessUnitDto = employeeRepository.findMaxEmployeeCountByBusinessUnit(PageRequest.of(0, 1)).stream().map(projection -> {
            return new EmployeesCountInBusinessUnitDto(businessUnitMapper.toDto(projection.getBusinessUnit()), projection.getEmployeesCount());
        }).findAny().orElse(null);

        EmployeesCountInJobTitleDto bestEmployeesCountInJobTitleDto = employeeRepository.findMaxEmployeeCountByJobTitle(PageRequest.of(0, 1)).stream().map(projection -> {
            return new EmployeesCountInJobTitleDto(jobTitleMapper.toDto(projection.getJobTitle()), projection.getEmployeesCount());
        }).findAny().orElse(null);

        EmployeesCountInDepartmentDto bestEmployeesCountInDepartment = employeeRepository.findMaxEmployeeCountByDepartment(PageRequest.of(0, 1)).stream().map(projection -> {
            return new EmployeesCountInDepartmentDto(departmentMapper.toDto(projection.getDepartment()), projection.getEmployeesCount());
        }).findAny().orElse(null);


        return EmployeeAnalyticsDto.builder()
                .employeesCountByAgeGroupDtos(employeesCountByAgeGroupDtos)
                .employeesCountByGenderDtos(employeesCountByGenderDtos)
                .employeesCountByOffices(employeesCountByOfficesDtos)
                .employeesCountByBusinessUnitDtos(employeesCountByBusinessUnitsDtos)
                .employeesCountByJobTitleDtos(employeesCountByJobTitlesDtos)
                .employeesCountInDepartmentDtos(employeesCountByDepartmentsDtos)
                .employeesCountByExperienceDtos(employeesCountByExperienceDtos)
                .employeesHiredCountByYear(employeesHiredCountByYearDtos)
                .employeesLeftCountByYearDtos(employeesLeftCountByYearDtos)
                .employeesTotalCountByYearDtos(employeesTotalCountByYears)
                .employeesCountInBusinessUnitDto(bestEmployeesCountInBusinessUnitDto)
                .employeesCountInJobTitleDto(bestEmployeesCountInJobTitleDto)
                .employeesCountInDepartmentDto(bestEmployeesCountInDepartment)
                .build();
    }

    public void makeListOfTotalEmployeesCountPerYear(List<EmployeesTotalCountByYearDto> employeesTotalCountByYearDtoList, List<EmployeesHiredCountByYearDto> employeesHiredCountByYearDtosList, List<EmployeesLeftCountByYearDto> employeesLeftCountByYearDtosList){
        for (EmployeesHiredCountByYearDto employeesHiredCountByYearDto : employeesHiredCountByYearDtosList) {
            String currentYear = employeesHiredCountByYearDto.getYear();
            Long hiredDuringCurrentYear = employeesHiredCountByYearDto.getEmployeesCount();
            EmployeesTotalCountByYearDto employeesTotalCountByYear = new EmployeesTotalCountByYearDto();
            employeesTotalCountByYear.setYear(currentYear);
            employeesTotalCountByYear.setEmployeesCount(hiredDuringCurrentYear);
            for (EmployeesLeftCountByYearDto employeesLeftCountByYearDto : employeesLeftCountByYearDtosList) {
                if(employeesLeftCountByYearDto.getYear().equals(currentYear)){
                    if(!employeesTotalCountByYearDtoList.isEmpty()){
                        employeesTotalCountByYear.setEmployeesCount(employeesTotalCountByYear.getEmployeesCount() - employeesLeftCountByYearDto.getEmployeesCount() + employeesTotalCountByYearDtoList.get(employeesTotalCountByYearDtoList.size()-1).getEmployeesCount());
                    }else {
                        employeesTotalCountByYear.setEmployeesCount(employeesTotalCountByYear.getEmployeesCount() - employeesLeftCountByYearDto.getEmployeesCount());
                    }
                }
            }
            employeesTotalCountByYearDtoList.add(employeesTotalCountByYear);
        }

    }


}



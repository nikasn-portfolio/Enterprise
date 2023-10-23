package com.knits.enterprise.service.company;


import com.knits.enterprise.config.Constants;
import com.knits.enterprise.dto.common.PaginatedResponseDto;
import com.knits.enterprise.dto.company.EmployeeDto;
import com.knits.enterprise.dto.search.EmployeeSearchDto;
import com.knits.enterprise.dto.search.GenericSearchDto;
import com.knits.enterprise.exceptions.UserException;
import com.knits.enterprise.mapper.company.EmployeeMapper;
import com.knits.enterprise.model.company.Employee;
import com.knits.enterprise.model.enums.Gender;
import com.knits.enterprise.repository.company.EmployeeRepository;
import com.knits.enterprise.utils.Constant;
import com.knits.enterprise.utils.EmployeeExelUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.*;
import org.apache.poi.OldFileFormatException.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.WorkbookEvaluator;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeMapper employeeMapper;
    private final EmployeeRepository employeeRepository;


    @Transactional
    public EmployeeDto saveNewEmployee(EmployeeDto employeeDto) {
        Employee employee = employeeMapper.toEntity(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return employeeMapper.toDto(savedEmployee);
    }

    @Transactional
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


    public PaginatedResponseDto<EmployeeDto> listAll(GenericSearchDto<Employee> searchDto) {

        Page<Employee> employeesPage = employeeRepository.findAll(searchDto.getSpecification(), searchDto.getPageable());
        List<EmployeeDto> employeeDtos = employeeMapper.toDtos(employeesPage.getContent());

        return PaginatedResponseDto.<EmployeeDto>builder()
                .page(searchDto.getPage())
                .size(employeeDtos.size())
                .sortingFields(searchDto.getSort())
                .sortDirection(searchDto.getDir().name())
                .data(employeeDtos)
                .build();
    }

    public PageImpl<EmployeeDto> searchForEmployees(EmployeeSearchDto searchDto) {
        Page<Employee> foundEmployees = employeeRepository.findAll(searchDto.getSpecification(), searchDto.getPageable());
        List<EmployeeDto> listEmployeesDtos = employeeMapper.toDtos(foundEmployees.getContent());
        return new PageImpl<>(listEmployeesDtos, searchDto.getPageable(), foundEmployees.getTotalElements());
    }

    public ByteArrayOutputStream makeExelOfEmployees(EmployeeSearchDto searchDto) {
        Page<Employee> foundEmployees = employeeRepository.findAll(searchDto.getSpecification(), searchDto.getPageable());
        List<EmployeeDto> listEmployeesDtos = employeeMapper.toDtos(foundEmployees.getContent());
        Workbook wb = new HSSFWorkbook();
        CreationHelper creationHelper = wb.getCreationHelper();
        Sheet sheet = wb.createSheet("main");
        if (listEmployeesDtos.isEmpty()) return null;
        EmployeeExelUtils.intoExcel(sheet, listEmployeesDtos, creationHelper);
        // Write the output to a file
        /*try (OutputStream fileOut = new FileOutputStream("Employees-.xlsx")) {
            wb.write(fileOut);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
        try(ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            wb.write(byteArrayOutputStream);
            return byteArrayOutputStream;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addEmployeeFromExelFile(String fileName) {
        List<Employee> employees = new ArrayList<>();
        try {
            Path pathToFile = Files.find(Path.of("src/test/java/resources"), 1, (path, attr) -> path.toString().contains(fileName))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("File not found"));
            InputStream fis = Files.newInputStream(pathToFile);
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0); // Assuming the data is in the first sheet

            Iterator<Row> rowIterator = sheet.iterator();

            // Skip the header row (if present)
            if (rowIterator.hasNext()) {
                rowIterator.next(); // Skip the header row
            }
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Employee employee = createEmployeeFromRow(row);
                System.out.println(employee.toString());
                employees.add(employee);
            }
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        employeeRepository.saveAll(employees);
    }

    public Employee createEmployeeFromRow(Row row){
        Employee employee = new Employee();
        employee.setFirstName(row.getCell(0).getStringCellValue());
        employee.setLastName(row.getCell(1).getStringCellValue());
        employee.setEmail(row.getCell(2).getStringCellValue());
        employee.setBirthDate(row.getCell(3).getLocalDateTimeCellValue().toLocalDate());
        employee.setGender(Gender.valueOf(row.getCell(4).getStringCellValue()));
        employee.setStartDate(row.getCell(5).getLocalDateTimeCellValue().toLocalDate());
        employee.setEndDate(row.getCell(6) != null ? row.getCell(6).getLocalDateTimeCellValue().toLocalDate() : null);
        employee.setCompanyPhone(String.valueOf(row.getCell(7).getStringCellValue()));
        employee.setCompanyMobileNumber(String.valueOf(row.getCell(7).getStringCellValue()));
        employee.setBusinessUnit(null);
        employee.setOrganization(null);
        employee.setOffice(null);
        employee.setJobTitle(null);
        employee.setDepartment(null);
        employee.setDivision(null);
        employee.setSolidLineManager(null);
        return employee;
    }
}



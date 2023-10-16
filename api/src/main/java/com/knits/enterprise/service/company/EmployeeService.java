package com.knits.enterprise.service.company;


import com.knits.enterprise.dto.common.PaginatedResponseDto;
import com.knits.enterprise.dto.company.EmployeeDto;
import com.knits.enterprise.dto.search.EmployeeSearchDto;
import com.knits.enterprise.dto.search.GenericSearchDto;
import com.knits.enterprise.exceptions.UserException;
import com.knits.enterprise.mapper.company.EmployeeMapper;
import com.knits.enterprise.model.company.Employee;
import com.knits.enterprise.repository.company.EmployeeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
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

    public void makeExelOfEmployees(EmployeeSearchDto searchDto) {
        Page<Employee> foundEmployees = employeeRepository.findAll(searchDto.getSpecification(), searchDto.getPageable());
        List<EmployeeDto> listEmployeesDtos = employeeMapper.toDtos(foundEmployees.getContent());
        Workbook wb = new HSSFWorkbook();
        CreationHelper creationHelper = wb.getCreationHelper();
        Sheet sheet = wb.createSheet("main");
        for (int i = 0; i <= 16; i++) {
            sheet.setColumnWidth(i, 15*256);
        }
        if (listEmployeesDtos.isEmpty()) return;
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue(
                creationHelper.createRichTextString("id"));
        row.createCell(1).setCellValue(
                creationHelper.createRichTextString("firstName"));
        row.createCell(2).setCellValue(
                creationHelper.createRichTextString("lastname"));
        row.createCell(3).setCellValue(
                creationHelper.createRichTextString("email"));
        row.createCell(4).setCellValue(
                creationHelper.createRichTextString("birthDate"));
        row.createCell(5).setCellValue(
                creationHelper.createRichTextString("gender"));
        row.createCell(6).setCellValue(
                creationHelper.createRichTextString("startDate"));
        row.createCell(7).setCellValue(
                creationHelper.createRichTextString("endDate"));
        row.createCell(8).setCellValue(
                creationHelper.createRichTextString("companyPhone"));
        row.createCell(9).setCellValue(
                creationHelper.createRichTextString("role"));
        row.createCell(10).setCellValue(
                creationHelper.createRichTextString("businessUnit"));
        row.createCell(11).setCellValue(
                creationHelper.createRichTextString("organization"));
        row.createCell(12).setCellValue(
                creationHelper.createRichTextString("office"));
        row.createCell(13).setCellValue(
                creationHelper.createRichTextString("jobTitle"));
        row.createCell(14).setCellValue(
                creationHelper.createRichTextString("department"));
        row.createCell(15).setCellValue(
                creationHelper.createRichTextString("division"));
        row.createCell(16).setCellValue(
                creationHelper.createRichTextString("solidLineManager"));
        for (int i = 0; i < listEmployeesDtos.size(); i++) {
            row = sheet.createRow(i + 1);
            EmployeeDto employeeDto = listEmployeesDtos.get(i);
            row.createCell(0).setCellValue(employeeDto.getId());
            row.createCell(1).setCellValue(employeeDto.getFirstName() != null ? employeeDto.getFirstName().toString() : null);
            row.createCell(2).setCellValue(employeeDto.getLastName() != null ? employeeDto.getLastName().toString() : null);
            row.createCell(3).setCellValue(employeeDto.getEmail() != null ? employeeDto.getEmail().toString() : null);
            row.createCell(4).setCellValue(employeeDto.getBirthDate() != null ? employeeDto.getBirthDate().toString() : null);
            row.createCell(5).setCellValue(employeeDto.getGender() != null ? employeeDto.getGender().toString() : null);
            row.createCell(6).setCellValue(employeeDto.getStartDate() != null ? employeeDto.getStartDate().toString() : null);
            row.createCell(7).setCellValue(employeeDto.getEndDate() != null ? employeeDto.getEndDate().toString() : null);
            row.createCell(8).setCellValue(employeeDto.getCompanyPhone() != null ? employeeDto.getCompanyPhone().toString() : null);
            row.createCell(9).setCellValue(employeeDto.getRole() != null ? employeeDto.getRole().toString() : null);
            row.createCell(10).setCellValue(employeeDto.getBusinessUnit() != null ? employeeDto.getBusinessUnit().toString() : null);
            row.createCell(11).setCellValue(employeeDto.getOrganization() != null ? employeeDto.getOrganization().toString() : null);
            row.createCell(12).setCellValue(employeeDto.getOffice() != null ? employeeDto.getOffice().toString() : null);
            row.createCell(13).setCellValue(employeeDto.getJobTitle() != null ? employeeDto.getJobTitle().toString() : null);
            row.createCell(14).setCellValue(employeeDto.getDepartment() != null ? employeeDto.getDepartment().toString() : null);
            row.createCell(15).setCellValue(employeeDto.getDivision() != null ? employeeDto.getDivision().toString() : null);
            row.createCell(16).setCellValue(employeeDto.getSolidLineManager() != null ? employeeDto.getSolidLineManager().toString() : null);

        }
        // Write the output to a file
        try (OutputStream fileOut = new FileOutputStream("Employees-.xlsx")) {
            wb.write(fileOut);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}



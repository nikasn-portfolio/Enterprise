package com.knits.enterprise.utils;

import com.knits.enterprise.dto.company.EmployeeDto;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;

public class EmployeeExelUtils {
    public static void intoExcel(Sheet sheet, List<EmployeeDto> listEmployeesDtos, CreationHelper creationHelper) {
        setWidthOfColumns(sheet);
        initEmployeeExcelHeaders(sheet,creationHelper);
        for (int i = 0; i < listEmployeesDtos.size(); i++) {
            Row row = sheet.createRow(i + 1);
            EmployeeDto employeeDto = listEmployeesDtos.get(i);
            row.createCell(0).setCellValue(employeeDto.getFirstName() != null ? employeeDto.getFirstName().toString() : null);
            row.createCell(1).setCellValue(employeeDto.getLastName() != null ? employeeDto.getLastName().toString() : null);
            row.createCell(2).setCellValue(employeeDto.getEmail() != null ? employeeDto.getEmail().toString() : null);
            row.createCell(3).setCellValue(employeeDto.getBirthDate() != null ? employeeDto.getBirthDate().toString() : null);
            row.createCell(4).setCellValue(employeeDto.getGender() != null ? employeeDto.getGender().toString() : null);
            row.createCell(5).setCellValue(employeeDto.getStartDate() != null ? employeeDto.getStartDate().toString() : null);
            row.createCell(6).setCellValue(employeeDto.getEndDate() != null ? employeeDto.getEndDate().toString() : null);
            row.createCell(7).setCellValue(employeeDto.getCompanyPhone() != null ? employeeDto.getCompanyPhone().toString() : null);
            row.createCell(8).setCellValue(employeeDto.getRole() != null ? employeeDto.getRole().toString() : null);
            row.createCell(9).setCellValue(employeeDto.getBusinessUnit() != null ? employeeDto.getBusinessUnit().toString() : null);
            row.createCell(10).setCellValue(employeeDto.getOrganization() != null ? employeeDto.getOrganization().toString() : null);
            row.createCell(11).setCellValue(employeeDto.getOffice() != null ? employeeDto.getOffice().toString() : null);
            row.createCell(12).setCellValue(employeeDto.getJobTitle() != null ? employeeDto.getJobTitle().toString() : null);
            row.createCell(13).setCellValue(employeeDto.getDepartment() != null ? employeeDto.getDepartment().toString() : null);
            row.createCell(14).setCellValue(employeeDto.getDivision() != null ? employeeDto.getDivision().toString() : null);
            row.createCell(15).setCellValue(employeeDto.getSolidLineManager() != null ? employeeDto.getSolidLineManager().toString() : null);

        }
    }
    public static void initEmployeeExcelHeaders(Sheet sheet, CreationHelper creationHelper) {
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue(
                creationHelper.createRichTextString("firstName"));
        row.createCell(1).setCellValue(
                creationHelper.createRichTextString("lastname"));
        row.createCell(2).setCellValue(
                creationHelper.createRichTextString("email"));
        row.createCell(3).setCellValue(
                creationHelper.createRichTextString("birthDate"));
        row.createCell(4).setCellValue(
                creationHelper.createRichTextString("gender"));
        row.createCell(5).setCellValue(
                creationHelper.createRichTextString("startDate"));
        row.createCell(6).setCellValue(
                creationHelper.createRichTextString("endDate"));
        row.createCell(7).setCellValue(
                creationHelper.createRichTextString("companyPhone"));
        row.createCell(8).setCellValue(
                creationHelper.createRichTextString("role"));
        row.createCell(9).setCellValue(
                creationHelper.createRichTextString("businessUnit"));
        row.createCell(10).setCellValue(
                creationHelper.createRichTextString("organization"));
        row.createCell(11).setCellValue(
                creationHelper.createRichTextString("office"));
        row.createCell(12).setCellValue(
                creationHelper.createRichTextString("jobTitle"));
        row.createCell(13).setCellValue(
                creationHelper.createRichTextString("department"));
        row.createCell(14).setCellValue(
                creationHelper.createRichTextString("division"));
        row.createCell(15).setCellValue(
                creationHelper.createRichTextString("solidLineManager"));
    }

    public static void setWidthOfColumns(Sheet sheet){
        for (int i = 0; i <= 16; i++) {
            sheet.setColumnWidth(i, 15*256);
        }
    }
}

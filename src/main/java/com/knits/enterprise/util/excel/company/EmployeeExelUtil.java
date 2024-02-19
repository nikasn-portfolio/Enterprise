package com.knits.enterprise.util.excel.company;

import com.knits.enterprise.dto.company.EmployeeDto;
import com.knits.enterprise.model.company.Employee;
import com.knits.enterprise.model.enums.Gender;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class EmployeeExelUtil {
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
        row.createCell(0).setCellValue(creationHelper.createRichTextString(ExcelEmployeeTitleKey.FIRST_NAME_KEY));
        row.createCell(1).setCellValue(creationHelper.createRichTextString(ExcelEmployeeTitleKey.LAST_NAME_KEY));
        row.createCell(2).setCellValue(creationHelper.createRichTextString(ExcelEmployeeTitleKey.EMAIL_KEY));
        row.createCell(3).setCellValue(creationHelper.createRichTextString(ExcelEmployeeTitleKey.BIRTH_DATE_KEY));
        row.createCell(4).setCellValue(creationHelper.createRichTextString(ExcelEmployeeTitleKey.GENDER_KEY));
        row.createCell(5).setCellValue(creationHelper.createRichTextString(ExcelEmployeeTitleKey.START_DATE_KEY));
        row.createCell(6).setCellValue(creationHelper.createRichTextString(ExcelEmployeeTitleKey.END_DATE_KEY));
        row.createCell(7).setCellValue(creationHelper.createRichTextString(ExcelEmployeeTitleKey.COMPANY_PHONE_KEY));
        row.createCell(8).setCellValue(creationHelper.createRichTextString(ExcelEmployeeTitleKey.ROLE_KEY));
        row.createCell(9).setCellValue(creationHelper.createRichTextString(ExcelEmployeeTitleKey.BUSINESS_UNIT_KEY));
        row.createCell(10).setCellValue(creationHelper.createRichTextString(ExcelEmployeeTitleKey.ORGANIZATION_KEY));
        row.createCell(11).setCellValue(creationHelper.createRichTextString(ExcelEmployeeTitleKey.OFFICE_KEY));
        row.createCell(12).setCellValue(creationHelper.createRichTextString(ExcelEmployeeTitleKey.JOB_TITLE_KEY));
        row.createCell(13).setCellValue(creationHelper.createRichTextString(ExcelEmployeeTitleKey.DEPARTMENT_KEY));
        row.createCell(14).setCellValue(creationHelper.createRichTextString(ExcelEmployeeTitleKey.DIVISION_KEY));
        row.createCell(15).setCellValue(creationHelper.createRichTextString(ExcelEmployeeTitleKey.SOLID_LINE_MANAGER_KEY));
    }

    public static void setWidthOfColumns(Sheet sheet){
        for (int i = 0; i <= 16; i++) {
            sheet.setColumnWidth(i, 15*256);
        }
    }

    public static Employee createEmployeeFromRow(Row row){
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

    public static InputStream findFileStream(String fileName){
        InputStream fis = null;
        try{
            Path pathToFile = Files.find(Path.of("src/test/java/resources"), 1, (path, attr) -> path.toString().contains(fileName))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("File not found"));
            fis = Files.newInputStream(pathToFile);
        }catch (Exception e){
        }
        return fis;
    }

    public static ByteArrayOutputStream createByteOutputStreamFromWorkbook(Workbook workbook){
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            workbook.write(bos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return bos;
    }
    public static class ExcelEmployeeTitleKey{
        private static final String FIRST_NAME_KEY = "firstName";
        private static final String LAST_NAME_KEY = "lastName";
        private static final String EMAIL_KEY = "email";
        private static final String BIRTH_DATE_KEY = "birthDate";
        private static final String GENDER_KEY = "gender";

        private static final String START_DATE_KEY = "startDate";
        private static final String END_DATE_KEY = "endDate";

        private static final String COMPANY_PHONE_KEY = "companyPhone";

        private static final String ROLE_KEY = "role";
        private static final String BUSINESS_UNIT_KEY = "businessUnit";

        private static final String ORGANIZATION_KEY = "organization";
        private static final String OFFICE_KEY = "office";
        private static final String JOB_TITLE_KEY = "jobTitle";
        private static final String DEPARTMENT_KEY = "department";
        private static final String DIVISION_KEY = "division";
        private static final String SOLID_LINE_MANAGER_KEY = "solidLineManager";
    }
}
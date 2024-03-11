package com.knits.enterprise.dto.company;

import com.knits.enterprise.dto.common.OrganizationDto;

import com.knits.enterprise.model.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@SuperBuilder(toBuilder=true)
public class EmployeeDto extends AbstractActiveDto {

    private Long id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String email;

    @NotNull
    private String birthDate;

    @NotNull
    private Gender gender;

    @NotNull
    private String startDate;


    private String endDate;

    @NotNull
    private String companyPhone;

    @NotNull
    private String companyMobileNumber;

    private String role;

    private BusinessUnitDto businessUnit;

    private OrganizationDto organization;

    private OfficeDto office;

    private JobTitleDto jobTitle;

    private DepartmentDto department;

    private DivisionDto division;

    private EmployeeDto solidLineManager;
}


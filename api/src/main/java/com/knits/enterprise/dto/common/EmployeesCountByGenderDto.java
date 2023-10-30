package com.knits.enterprise.dto.common;

import com.knits.enterprise.model.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeesCountByGenderDto {
    private Gender gender;
    private Long employeesCount;
}

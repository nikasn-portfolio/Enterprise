package com.knits.enterprise.dto.analytics;

import com.knits.enterprise.dto.location.LocationDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeesCountByOfficeDto {
    private LocationDto office;
    private Long employeesCount;
}

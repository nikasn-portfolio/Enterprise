package com.knits.enterprise.model.analytics;

import com.knits.enterprise.model.location.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class EmployeesCountByOffice {
    private Location office;
    private Long employeesCount;

}

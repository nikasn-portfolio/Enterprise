package com.knits.enterprise.view;

import com.knits.enterprise.model.company.BusinessUnit;

public interface EmployeesCountInBusinessUnitView {
    BusinessUnit getBusinessUnit();
    Long getEmployeesCount();
}

package com.knits.enterprise.view;

import com.knits.enterprise.model.company.Department;

public interface EmployeesCountInDepartmentView {
    Department getDepartment();
    Long getEmployeesCount();
}

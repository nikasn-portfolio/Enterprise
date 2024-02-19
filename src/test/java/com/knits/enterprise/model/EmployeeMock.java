package com.knits.enterprise.model;

import com.knits.enterprise.model.company.Employee;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class EmployeeMock {
    public static Employee shallowEmployee(long id) {
        return Employee.builder()
                .id(id)
                .firstName("Mock name")
                .lastName("Mock lastName")
                .email("mock@mock.com")
                .birthDate(LocalDate.now())
                .startDate(LocalDate.now())
                .companyPhone("Mock phone")
                .companyMobileNumber("Mock mobile")
                .build();
    }
    public static Set<Employee> shallowSetOfEmployees(int size) {
        Set<Employee> employees = new HashSet<>();
        for (int i = 1; i <= size; i++) {
            employees.add(shallowEmployee(Long.valueOf(i)));
        }
        return employees;
    }

}

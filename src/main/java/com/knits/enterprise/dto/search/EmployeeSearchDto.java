package com.knits.enterprise.dto.search;

import com.knits.enterprise.model.company.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder(toBuilder=true)
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class EmployeeSearchDto extends GenericSearchDto<Employee>{

    private String firstName;
    private String lastName;
    private String gender;

    private String hireDateFrom;

    private String hireDateTo;

    private Long businessUnit;

    private Long department;

    private Long jobTitle;

    private Long office;



    protected void addFilters(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder, List<Predicate> filters) {

        if (StringUtils.isNotEmpty(lastName)){
            Predicate lastNameAsPredicate = criteriaBuilder.equal(root.get("lastName"), "%" + lastName + "%");
            filters.add(lastNameAsPredicate);
        }

        if (StringUtils.isNotEmpty(gender)){
            Predicate genderAsPredicate = criteriaBuilder.equal(root.get("gender"), gender);
            filters.add(genderAsPredicate);
        }

        if (StringUtils.isNotEmpty(firstName)){
            Predicate firstNameAsPredicate = criteriaBuilder.like(root.get("firstName"), "%" + firstName + "%");
            filters.add(firstNameAsPredicate);
        }

        if (StringUtils.isNotEmpty(hireDateFrom)){
            LocalDate from = LocalDate.parse(hireDateFrom, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            Predicate hiringDateFromAsPredicate = criteriaBuilder.greaterThanOrEqualTo(root.get("startDate"), from);
            filters.add(hiringDateFromAsPredicate);
        }

        if (StringUtils.isNotEmpty(hireDateFrom)){
            LocalDate from = LocalDate.parse(hireDateFrom, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            Predicate hiringDateFromAsPredicate = criteriaBuilder.greaterThanOrEqualTo(root.get("startDate"), from);
            filters.add(hiringDateFromAsPredicate);
        }

        if (StringUtils.isNotEmpty(hireDateTo)){
            LocalDate to = LocalDate.parse(hireDateTo, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            Predicate endingDateFromAsPredicate = criteriaBuilder.lessThanOrEqualTo(root.get("endDate"), to);
            filters.add(endingDateFromAsPredicate);
        }

        if (office != null){
            Predicate officeAsPredicate = criteriaBuilder.equal(root.get("office").get("id"), office);
            filters.add(officeAsPredicate);
        }

        if (businessUnit != null){
            Predicate businessUnitAsPredicate = criteriaBuilder.equal(root.get("businessUnit").get("id"), businessUnit);
            filters.add(businessUnitAsPredicate);
        }

        if(department != null){
            Predicate departmentAsPredicate = criteriaBuilder.equal(root.get("department").get("id"), department);
            filters.add(departmentAsPredicate);
        }

        if(jobTitle != null){
            Predicate jobTitleAsPredicate = criteriaBuilder.equal(root.get("jobTitle").get("id"), jobTitle);
            filters.add(jobTitleAsPredicate);
        }

    }


}

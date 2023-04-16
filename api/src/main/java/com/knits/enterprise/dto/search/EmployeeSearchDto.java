package com.knits.enterprise.dto.search;

import com.knits.enterprise.model.company.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
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
    private Long businessUnitId;
    private Long departmentId;
    private Long jobTitleId;
    private Long countryId;


    protected void addFilters(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder, List<Predicate> filters) {

        if (StringUtils.isNotEmpty(lastName)){
            Predicate lastNameAsPredicate = criteriaBuilder.equal(root.get("lastName"), lastName);
            filters.add(lastNameAsPredicate);
        }

        if (StringUtils.isNotEmpty(gender)){
            Predicate genderAsPredicate = criteriaBuilder.equal(root.get("gender"), gender);
            filters.add(genderAsPredicate);
        }

        //firstName
        if(Strings.isNotBlank(firstName)) {
            Predicate firstNameAsPredicate = criteriaBuilder.equal(root.get(firstName), firstName);
            filters.add(firstNameAsPredicate);
        }

        // hireDateFrom
        if(Strings.isNotBlank(hireDateFrom)) {
            LocalDate dateFrom = LocalDate.parse(hireDateFrom, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            Predicate hireDateFromAsPredicate = criteriaBuilder.greaterThanOrEqualTo(root.get("startDate"), dateFrom);
            filters.add(hireDateFromAsPredicate);
        }

        // hireDateTo
        if(Strings.isNotBlank(hireDateTo)) {
            LocalDate dateTo = LocalDate.parse(hireDateTo, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            Predicate hireDateToAsPredicate = criteriaBuilder.lessThanOrEqualTo(root.get("startDate"), dateTo);
            filters.add(hireDateToAsPredicate);
        }

        // businessUnit
        if(businessUnitId != null) {
            Predicate businessUnitIdAsPredicate = criteriaBuilder.equal(root.get("businessUnit").get("id"), businessUnitId);
            filters.add(businessUnitIdAsPredicate);
        }

        // department
        if(departmentId != null) {
            Predicate departmentIdAsPredicate = criteriaBuilder.equal(root.get("department").get("id"), businessUnitId);
            filters.add(departmentIdAsPredicate);
        }

        // jobTitle
        if(jobTitleId != null) {
            Predicate jobTitleIdAsPredicate = criteriaBuilder.equal(root.get("jobTitle").get("id"), jobTitleId);
            filters.add(jobTitleIdAsPredicate);
        }

        // office
        if(countryId != null) {
            Predicate countryIdAsPredicate = criteriaBuilder.equal(root.get("office").get("country").get("id"), countryId);
            filters.add(countryIdAsPredicate);
        }
    }
}

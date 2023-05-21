package com.knits.enterprise.dto.search;

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
public class EmploymentContractSearchDto<EmploymentContract> extends GenericSearchDto<EmploymentContract> {

    private String lastName;
    private String creationDateFrom;
    private String creationDateTo;
    private String fileName;
    private String fileType;

    protected void addFilters(Root<EmploymentContract> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder, List<Predicate> filters) {

        // lastName
        if (StringUtils.isNotEmpty(lastName)){
            Predicate lastNameAsPredicate = criteriaBuilder.equal(root.get("lastName"), lastName);
            filters.add(lastNameAsPredicate);
        }

        // creationDateFrom-To
        if(Strings.isNotBlank(creationDateFrom) && Strings.isNotBlank(creationDateTo)) {
            LocalDate dateFrom = LocalDate.parse(creationDateFrom, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            LocalDate dateTo = LocalDate.parse(creationDateFrom, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            Predicate creationDateFromAsPredicate = criteriaBuilder.greaterThanOrEqualTo(root.get("startDate"), dateFrom);
            filters.add(creationDateFromAsPredicate);
        }

        // fileName
        if (StringUtils.isNotEmpty(fileName)){
            Predicate fileNameAsPredicate = criteriaBuilder.equal(root.get("fileName"), fileName);
            filters.add(fileNameAsPredicate);
        }

        // fileType
        if(fileType != null) {
            Predicate fileTypeAsPredicate = criteriaBuilder.equal(root.get("fileType"), fileType);
            filters.add(fileTypeAsPredicate);
        }

    }

}

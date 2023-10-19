package com.knits.enterprise.dto.search;

import com.knits.enterprise.model.company.BusinessUnit;
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
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder(toBuilder=true)
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class BusinessUnitSearchDto extends GenericSearchDto<BusinessUnit>{
    private String name;


    protected void addFilters(Root<BusinessUnit> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder, List<Predicate> filters) {
        if (StringUtils.isNotEmpty(name)){
            Predicate nameAsPredicate = criteriaBuilder.like(root.get("name"), "%" + name + "%");
            filters.add(nameAsPredicate);
        }

    }
}

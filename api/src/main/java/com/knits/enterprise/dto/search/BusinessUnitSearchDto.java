package com.knits.enterprise.dto.search;

import com.knits.enterprise.model.company.BusinessUnit;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder=true)
@Slf4j
public class BusinessUnitSearchDto extends GenericSearchDto<BusinessUnit>{
    private String name;


    protected void addFilters(Root<BusinessUnit> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder, List<Predicate> filters) {
        if (StringUtils.isNotEmpty(name)){
            Predicate nameAsPredicate = criteriaBuilder.like(root.get("name"), "%" + name + "%");
            filters.add(nameAsPredicate);
        }

    }

    public void setFieldsIfNull(Integer page, Integer limit, String sort, Sort.Direction dir, String name){
        if(page != null) this.setPage(page);
        if(limit != null) this.setLimit(limit);
        if(sort != null) this.setSort(sort);
        if(dir != null) this.setDir(dir);
        if(name != null) this.setName(name);
    }
}

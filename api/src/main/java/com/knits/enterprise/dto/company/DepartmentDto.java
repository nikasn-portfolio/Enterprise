package com.knits.enterprise.dto.company;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder=true)
public class DepartmentDto extends AbstractOrganizationStructureDto{

}


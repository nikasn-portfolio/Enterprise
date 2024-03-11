package com.knits.enterprise.dto.company;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@SuperBuilder(toBuilder=true)
public class JobTitleDto extends AbstractOrganizationStructureDto{

}

package com.knits.enterprise.dto.company;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder(toBuilder=true)
public class GroupDto extends AbstractOrganizationStructureDto{

}

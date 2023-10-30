package com.knits.enterprise.dto.company;

import com.knits.enterprise.dto.security.UserDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@SuperBuilder(toBuilder=true)
public class AbstractOrganizationStructureDto extends AbstractActiveDto {

    private Long id;
    @NotNull(message = "Name is mandatory")
    @Size(max = 255, message = "Name should not exceed 255 characters")
    @NotBlank(message = "Name should be not empty")
    private String name;
    @Size(max = 255, message = "Description should not exceed 255 characters")
    private String description;

    private String startDate;
    private String endDate;
    private UserDto createdBy;
}

package com.knits.enterprise.dto.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;
@NoArgsConstructor
@SuperBuilder(toBuilder=true)
@Data
public class RoleDto {
    private Long id;
    @NotEmpty
    private String name;
}

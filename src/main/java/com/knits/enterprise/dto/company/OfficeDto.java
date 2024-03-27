package com.knits.enterprise.dto.company;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@SuperBuilder(toBuilder=true)
@Data
public class OfficeDto {
    private Long id;
    private String name;
    private String telephone;
    private String email;
}


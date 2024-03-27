package com.knits.enterprise.dto.company;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder(toBuilder=true)
public abstract class AbstractActiveDto {
        private boolean active;
}

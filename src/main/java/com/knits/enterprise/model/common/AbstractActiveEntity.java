package com.knits.enterprise.model.common;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@MappedSuperclass
@NoArgsConstructor
@Data
@SuperBuilder(toBuilder=true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AbstractActiveEntity{
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "active", columnDefinition = "boolean default true")
    private boolean active;
}

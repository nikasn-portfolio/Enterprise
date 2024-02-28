package com.knits.enterprise.model.company;

import com.knits.enterprise.model.common.AbstractActiveEntity;
import com.knits.enterprise.model.security.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.SEQUENCE;

@MappedSuperclass
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder=true)
public abstract class OrganizationalEntity{
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "active", columnDefinition = "boolean default true")
    private boolean active;
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;
}

package com.knits.enterprise.model.company;

import com.knits.enterprise.model.security.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder=true)
@Table(name = "job_title")
public class JobTitle extends AbstractOrganizationStructure implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "active", columnDefinition = "boolean default true")
    private boolean active = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

}

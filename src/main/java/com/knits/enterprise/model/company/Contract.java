package com.knits.enterprise.model.company;

import com.knits.enterprise.model.common.MediaFile;
import com.knits.enterprise.model.security.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "contract")
public class Contract{
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "binary_id")
    private MediaFile mediaFile;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn (name = "creator_id")
    private User createdBy;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "employee_id")
    private  Employee employee;

    @Column (name = "active")
    private Boolean active = true;

    @Column
    private LocalDateTime createdAt;

    @PrePersist
    public void onSave() {
        if (this.id == null) {
            this.createdAt = LocalDateTime.now();
        }
    }

    public Contract(Long id, MediaFile mediaFile, Employee employee, Boolean active, LocalDateTime createdAt) {
        this.id = id;
        this.mediaFile = mediaFile;
        this.employee = employee;
        this.active = active;
        this.createdAt = createdAt;
    }
}

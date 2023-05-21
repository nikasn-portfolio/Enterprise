package com.knits.enterprise.model.common;

import com.knits.enterprise.model.company.Employee;
import com.knits.enterprise.model.security.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "employment_contract")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder=true)
public class EmploymentContract extends AbstractActiveEntity{

    @Column(nullable = false)
    private String fileName;
    @Column(nullable = false)
    private String fileType;
    @Lob
    @Column(nullable = false)
    private byte[] data;
    @Column(nullable = false)
    private LocalDateTime created;
    @OneToOne(fetch = FetchType.LAZY)
    private User created_by;
    @ManyToOne(fetch = FetchType.LAZY)
    private Employee employee;

    public EmploymentContract(String fileName, String contentType, byte[] bytes) {
        this.fileName = fileName;
        this.fileType = contentType;
        this.data = bytes;
    }
}

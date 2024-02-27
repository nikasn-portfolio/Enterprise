package com.knits.enterprise.model.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "files")
public class BinaryData{
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    private String title;

    private Long size;

    @Column(name = "content_type")
    private String contentType;

    @Lob
    private byte[] bytes;
}
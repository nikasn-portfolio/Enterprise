package com.knits.enterprise.model.common;

import com.knits.enterprise.model.company.OrganizationalEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder=true)
@Table(name = "organization")
public class Organization extends OrganizationalEntity {

    @Column
    private String alias;

    @Column(nullable = false)
    private String vatNumber;

    @Column(nullable = false)
    private String registrationCode;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "tax_registration_country_id")
    private Country taxRegistrationCountry;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address legalAddress;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "contact_id")
    private Contact contactPerson;
}

package com.knits.enterprise.model.location;

import com.knits.enterprise.model.common.Address;
import com.knits.enterprise.model.company.OrganizationalEntity;
import com.knits.enterprise.model.enums.LocationUsageType;
import com.knits.enterprise.model.enums.OwnershipType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@NoArgsConstructor
@SuperBuilder(toBuilder=true)
@Data
@Table(name = "location")
public class Location extends OrganizationalEntity {

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private OwnershipType ownership = OwnershipType.valueOf("OUR_PREMISES");

    @Column(name = "map_coordinates")
    private boolean mapCoordinates;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;

    @Column(name = "use")
    @Enumerated(EnumType.STRING)
    private LocationUsageType use;

}

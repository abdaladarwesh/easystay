package com.ntgschool.easystay.Entities;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserLocation {
    private String displayName;
    private Long houseNumber;
    private String road;
    private String neighbourhood;
    private String suburb;
    private Location location;
}

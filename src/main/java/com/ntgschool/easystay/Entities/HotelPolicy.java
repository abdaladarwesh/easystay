package com.ntgschool.easystay.Entities;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HotelPolicy {
    private String cancellation;
    private String children;
    private String pets;
    private String smoking;
}

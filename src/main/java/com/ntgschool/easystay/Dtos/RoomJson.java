package com.ntgschool.easystay.Dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomJson {
    private Long id;
    private String name;
    private Integer capacityAdults;
    private Integer capacityChildren;
    private String bedType;
    private Integer sizeM2;
    private Double pricePerNight;
    private Boolean refundable;
    private Boolean breakfastIncluded;
}
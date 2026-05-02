package com.ntgschool.easystay.Dtos.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomRequest {
    private String name;
    private Long capacity;
    private Integer capacityChildren;
    private String bedType;
    private Integer sizeM2;
    private Double price;
    private Boolean refundable;
    private Boolean breakfastIncluded;
    private List<FacilityRequest> facilities;
    private HotelRequest hotel;
}

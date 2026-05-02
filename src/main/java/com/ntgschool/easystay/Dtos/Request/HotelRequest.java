package com.ntgschool.easystay.Dtos.Request;

import com.ntgschool.easystay.Entities.HotelPolicy;
import com.ntgschool.easystay.Entities.Location;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HotelRequest {
    private Long id;
    private String name;
    private String address;
    private Location location;
    private Integer stars;
    private Double rating;
    private Integer reviewCount;
    private Double pricePerNight;
    private String currency;
    private String description;
    private String mainImage;
    private List<String> images;
    private String propertyType;
    private String checkInFrom;
    private String checkOutUntil;
    private String phone;
    private String email;
    private String website;
    private Double distanceFromCenterKm;
    private Double distanceFromAirportKm;
    private List<String> amenities;
    private List<String> popularFacilities;
    private HotelPolicy policies;
    private List<RoomRequest> rooms;
}

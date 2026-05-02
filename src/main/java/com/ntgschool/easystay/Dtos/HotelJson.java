package com.ntgschool.easystay.Dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HotelJson {
    private Long id;
    private String name;
    private String address;
    private String mainImage;
    private List<String> images;
    private String description;

    private double latitude;
    private double longitude;
    private String city;
    private String country;

    private Integer stars;
    private Double rating;
    private Integer reviewCount;
    private Double pricePerNight;
    private String currency;
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
    private PoliciesJson policies;

    private List<RoomJson> rooms;

    @Getter
    @Setter
    public static class PoliciesJson {
        private String cancellation;
        private String children;
        private String pets;
        private String smoking;
    }
}
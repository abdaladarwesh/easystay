package com.ntgschool.easystay.Dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HotelJson {
    private Long id;
    private String name;
    private String mainImage;
    private List<String> images;

    private double latitude;
    private double longitude;
    private String city;
    private String country;

    private List<String> amenities;

    private List<RoomJson> rooms;
}
package com.ntgschool.easystay.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "hotel")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String address;

    @Embedded
    private Location location;

    @Column
    private Integer stars;

    @Column
    private Double rating;

    @Column
    private Integer reviewCount;

    @Column
    private Double pricePerNight;

    @Column
    private String currency;

    @Column(nullable = false)
    private String mainImage;

    @ElementCollection
    @CollectionTable(name = "hotel_images", joinColumns = @JoinColumn(name = "hotel_id"))
    @Column(name = "image_url")
    private List<String> images;

    @Column(nullable = false)
    private String description;

    @Column
    private String propertyType;

    @Column
    private String checkInFrom;

    @Column
    private String checkOutUntil;

    @Column
    private String phone;

    @Column
    private String email;

    @Column
    private String website;

    @Column
    private Double distanceFromCenterKm;

    @Column
    private Double distanceFromAirportKm;

    @ElementCollection
    @CollectionTable(name = "hotel_amenities", joinColumns = @JoinColumn(name = "hotel_id"))
    @Column(name = "amenity")
    private List<String> amenities;

    @ElementCollection
    @CollectionTable(name = "hotel_popular_facilities", joinColumns = @JoinColumn(name = "hotel_id"))
    @Column(name = "facility_name")
    private List<String> popularFacilities;

    @Embedded
    private HotelPolicy policies;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Room> rooms = new ArrayList<>();
}
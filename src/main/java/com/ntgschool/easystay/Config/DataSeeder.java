package com.ntgschool.easystay.Config;

import com.fasterxml.jackson.annotation.*;
import com.ntgschool.easystay.Dtos.HotelJson;
import com.ntgschool.easystay.Dtos.RoomJson;
import com.ntgschool.easystay.Entities.*;
import com.ntgschool.easystay.Repos.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;

@Configuration
@RequiredArgsConstructor
public class DataSeeder {

    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;
    private final FacilityRepository facilityRepository;

    @Bean
    CommandLineRunner loadJsonData() {
        return args -> {

            if (hotelRepository.count() > 0) return;

            ObjectMapper mapper = new ObjectMapper();

            TypeReference<List<HotelJson>> type = new TypeReference<>() {};
            InputStream input = getClass().getResourceAsStream("/Data/hotels.json");

            List<HotelJson> hotels = mapper.readValue(input, type);

            for (HotelJson h : hotels) {

                Hotel hotel = Hotel.builder()
                        .name(h.getName())
                        .mainImage(h.getMainImage())
                        .images(h.getImages())
                        .description(h.getDescription())
                        .address(h.getAddress())
                        .amenities(h.getAmenities())
                        .checkInFrom(h.getCheckInFrom())
                        .checkOutUntil(h.getCheckOutUntil())
                        .currency(h.getCurrency())
                        .distanceFromAirportKm(h.getDistanceFromAirportKm())
                        .distanceFromCenterKm(h.getDistanceFromCenterKm())
                        .email(h.getEmail())
                        .phone(h.getPhone())
                        .policies(HotelPolicy.builder()
                        .cancellation(h.getPolicies().getCancellation())
                        .children(h.getPolicies().getChildren())
                        .pets(h.getPolicies().getPets())
                        .smoking(h.getPolicies().getSmoking())
                        .build()
                    )
                    .pricePerNight(h.getPricePerNight())
                    .propertyType(h.getPropertyType())
                    .rating(h.getRating())
                    .reviewCount(h.getReviewCount())
                    .popularFacilities(h.getPopularFacilities())
                    .stars(h.getStars())
                    .website(h.getWebsite())

                        .location(Location.builder()
                                .latitude(BigDecimal.valueOf(h.getLatitude()))
                                .longitude(BigDecimal.valueOf(h.getLongitude()))
                                .city(h.getCity())
                                .country(h.getCountry())
                                .build())
                        .build();

                hotelRepository.save(hotel);

                List<Room> rooms = new ArrayList<>();

                for (RoomJson r : h.getRooms()) {

                    List<Facility> facilities = new ArrayList<>();

                    for (String amenity : h.getAmenities()) {
                        Facility f = facilityRepository.findByName(amenity)
                                .orElseGet(() -> facilityRepository.save(
                                        Facility.builder().name(amenity).build()
                                ));
                        facilities.add(f);
                    }

                    Room room = Room.builder()
                            .name(r.getName())
                            .capacity(r.getCapacityAdults().longValue())
                            .capacityChildren(r.getCapacityChildren())
                            .bedType(r.getBedType())
                            .sizeM2(r.getSizeM2())
                            .price(BigDecimal.valueOf(r.getPricePerNight()))
                            .refundable(r.getRefundable())
                            .breakfastIncluded(r.getBreakfastIncluded())
                            .hotel(hotel)
                            .facilities(facilities)
                            .build();

                    rooms.add(room);
                }

                roomRepository.saveAll(rooms);

                hotel.setRooms(rooms);
                hotelRepository.save(hotel);
            }
        };
    }
}
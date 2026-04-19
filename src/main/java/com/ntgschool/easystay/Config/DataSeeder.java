package com.ntgschool.easystay.Config;

import com.ntgschool.easystay.Entities.*;
import com.ntgschool.easystay.Repos.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class DataSeeder {

    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;
    private final FacilityRepository facilityRepository;

    @Bean
    CommandLineRunner seedData() {
        return args -> {

            if (hotelRepository.count() > 0) return;

            // ===== Facilities =====
            Facility wifi = facilityRepository.save(Facility.builder().name("WiFi").build());
            Facility ac = facilityRepository.save(Facility.builder().name("Air Conditioning").build());
            Facility tv = facilityRepository.save(Facility.builder().name("Smart TV").build());
            Facility minibar = facilityRepository.save(Facility.builder().name("Mini Bar").build());
            Facility balcony = facilityRepository.save(Facility.builder().name("Balcony").build());

            // ===== Hotels with REAL coordinates =====

            // Cairo (Downtown approx)
            Hotel cairoHotel = Hotel.builder()
                    .name("Nile View Hotel")
                    .rooms(new ArrayList<Room>())
                    .location(Location.builder()
                            .latitude(new BigDecimal("30.0444"))
                            .longitude(new BigDecimal("31.2357"))
                            .city("Cairo")
                            .country("Egypt")
                            .build())
                    .build();

            // Giza (Pyramids area)
            Hotel gizaHotel = Hotel.builder()
                    .name("Pyramids Resort")
                    .rooms(new ArrayList<Room>())
                    .location(Location.builder()
                            .latitude(new BigDecimal("29.9792"))
                            .longitude(new BigDecimal("31.1342"))
                            .city("Giza")
                            .country("Egypt")
                            .build())
                    .build();

            hotelRepository.saveAll(List.of(cairoHotel, gizaHotel));

            // ===== Rooms =====
            Room r1 = Room.builder()
                    .capacity(2L)
                    .price(BigDecimal.valueOf(1200.0))
                    .hotel(cairoHotel)
                    .facilities(List.of(wifi, ac, tv))
                    .build();

            Room r2 = Room.builder()
                    .capacity(4L)
                    .price(BigDecimal.valueOf(2200.0))
                    .hotel(cairoHotel)
                    .facilities(List.of(wifi, ac, tv, minibar))
                    .build();

            Room r3 = Room.builder()
                    .capacity(2L)
                    .price(BigDecimal.valueOf(1500.0))
                    .hotel(gizaHotel)
                    .facilities(List.of(wifi, balcony))
                    .build();

            Room r4 = Room.builder()
                    .capacity(3L)
                    .price(BigDecimal.valueOf(1800.0))
                    .hotel(gizaHotel)
                    .facilities(List.of(wifi, ac, balcony, minibar))
                    .build();

            roomRepository.saveAll(List.of(r1, r2, r3, r4));

            // Maintain bidirectional consistency
            cairoHotel.setRooms(List.of(r1, r2));
            gizaHotel.setRooms(List.of(r3, r4));

            hotelRepository.saveAll(List.of(cairoHotel, gizaHotel));
        };
    }
}
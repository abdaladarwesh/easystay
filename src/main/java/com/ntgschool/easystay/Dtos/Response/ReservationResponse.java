package com.ntgschool.easystay.Dtos.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationResponse {

    private Long id;

    private HotelResponse hotel;

    private RoomResponse room;

    private LocalDateTime checkIn;

    private LocalDateTime checkOut;
}

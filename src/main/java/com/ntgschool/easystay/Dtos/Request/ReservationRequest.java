package com.ntgschool.easystay.Dtos.Request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationRequest {
    private Long hotelId;
    private Long roomId;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
}
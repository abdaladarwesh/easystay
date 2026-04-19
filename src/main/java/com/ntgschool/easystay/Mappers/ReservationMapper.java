package com.ntgschool.easystay.Mappers;

import com.ntgschool.easystay.Dtos.Response.ReservationResponse;
import com.ntgschool.easystay.Entities.Reservation;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReservationMapper {
    ReservationResponse toReservationResponse(Reservation reservation);
    List<ReservationResponse> toReservationResponses(List<Reservation> reservations);
}

package com.ntgschool.easystay.Services;

import com.ntgschool.easystay.Dtos.Request.ReservationRequest;
import com.ntgschool.easystay.Entities.Reservation;

import java.util.List;

public interface ReservationService {
    Reservation addReservation(ReservationRequest request);
    void cancelReservation(Long reservationId);
    List<Reservation> getAllReservations();
}

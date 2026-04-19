package com.ntgschool.easystay.Exceptions;

public class ReservationNotFoundException extends RuntimeException {
    private Long id;
    public ReservationNotFoundException(Long id) {
        super("Reservation With id " + id + " is Not found");
        this.id = id;
    }
}

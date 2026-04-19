package com.ntgschool.easystay.Exceptions;

import java.util.UUID;

public class UnAuthorizedAccessToReservationException extends RuntimeException {
    private Long reservationId;
    private UUID userId;
    public UnAuthorizedAccessToReservationException(Long reservationId, UUID userId) {
        super("User with id " + userId + " doesn't have access to reservation with id " + reservationId);
        this.reservationId = reservationId;
        this.userId = userId;
    }
}

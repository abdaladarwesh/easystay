package com.ntgschool.easystay.Services.impl;

import com.ntgschool.easystay.Dtos.Request.ReservationRequest;
import com.ntgschool.easystay.Entities.Hotel;
import com.ntgschool.easystay.Entities.Reservation;
import com.ntgschool.easystay.Entities.Room;
import com.ntgschool.easystay.Entities.User;
import com.ntgschool.easystay.Exceptions.HotelNotFoundException;
import com.ntgschool.easystay.Exceptions.ReservationNotFoundException;
import com.ntgschool.easystay.Exceptions.RoomNotFoundException;
import com.ntgschool.easystay.Exceptions.UnAuthorizedAccessToReservationException;
import com.ntgschool.easystay.Repos.HotelRepository;
import com.ntgschool.easystay.Repos.ReservationRepository;
import com.ntgschool.easystay.Repos.RoomRepository;
import com.ntgschool.easystay.Repos.UserRepository;
import com.ntgschool.easystay.Security.SecurityUtils;
import com.ntgschool.easystay.Security.UserPrincipal;
import com.ntgschool.easystay.Services.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private final SecurityUtils securityUtils;

    @Override
    public Reservation addReservation(ReservationRequest request) {
        UserPrincipal userPrincipal = securityUtils.getCurrentUser();
        Hotel hotel = hotelRepository.findById(request.getHotelId())
                .orElseThrow(() -> new HotelNotFoundException(request.getHotelId()));
        Room room = roomRepository.findById(request.getRoomId())
                .orElseThrow(() -> new RoomNotFoundException(request.getRoomId()));
        Reservation reservation = Reservation.builder()
                .room(room)
                .hotel(hotel)
                .user(userPrincipal.getUser())
                .checkIn(request.getCheckIn())
                .checkOut(request.getCheckOut())
                .build();
        return reservationRepository.save(reservation);

    }

    @Override
    public void cancelReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ReservationNotFoundException(reservationId));

        User user = securityUtils.getCurrentUser().getUser();

        if (!reservation.getUser().getEmail().equals(user.getEmail())) throw new UnAuthorizedAccessToReservationException(reservationId, user.getId());
        reservationRepository.deleteById(reservationId);
    }

    @Override
    public List<Reservation> getAllReservations() {
        User user = securityUtils.getCurrentUser().getUser();
        return reservationRepository.findAllByUser(user);
    }
}

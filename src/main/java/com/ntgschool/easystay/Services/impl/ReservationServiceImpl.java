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

    @Override
    public Reservation addReservation(ReservationRequest request) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("User Doesn't exist with id " + email)
        );
        Hotel hotel = hotelRepository.findById(request.getHotelId())
                .orElseThrow(() -> new HotelNotFoundException(request.getHotelId()));
        Room room = roomRepository.findById(request.getRoomId())
                .orElseThrow(() -> new RoomNotFoundException(request.getRoomId()));
        Reservation reservation = Reservation.builder()
                .room(room)
                .hotel(hotel)
                .user(user)
                .checkIn(request.getCheckIn())
                .checkOut(request.getCheckOut())
                .build();
        return reservationRepository.save(reservation);

    }

    @Override
    public void cancelReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ReservationNotFoundException(reservationId));

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("User Doesn't exist with id " + email)
        );

        if (!reservation.getUser().equals(user)) throw new UnAuthorizedAccessToReservationException(reservationId, user.getId());
        reservationRepository.deleteById(reservationId);
    }

    @Override
    public List<Reservation> getAllReservations() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("User Doesn't exist with id " + email)
        );
        return reservationRepository.findAllByUser(user);
    }
}

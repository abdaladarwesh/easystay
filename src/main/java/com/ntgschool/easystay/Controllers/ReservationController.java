package com.ntgschool.easystay.Controllers;

import com.ntgschool.easystay.Dtos.Request.ReservationRequest;
import com.ntgschool.easystay.Dtos.Response.ReservationResponse;
import com.ntgschool.easystay.Mappers.ReservationMapper;
import com.ntgschool.easystay.Services.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reservations")
public class ReservationController {
    private final ReservationService reservationService;
    private final ReservationMapper reservationMapper;


    @GetMapping
    public ResponseEntity<List<ReservationResponse>> getAllReservations(){
        return ResponseEntity.ok(reservationMapper.toReservationResponses(
                reservationService.getAllReservations()
        ));
    }

    @PostMapping
    public ResponseEntity<ReservationResponse> createReservation(@RequestBody ReservationRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                reservationMapper.toReservationResponse(
                        reservationService.addReservation(request)
                )
        );
    }
}

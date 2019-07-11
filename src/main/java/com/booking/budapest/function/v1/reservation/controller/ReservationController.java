package com.booking.budapest.function.v1.reservation.controller;

import com.booking.budapest.function.v1.reservation.dto.CreateReservationDto;
import com.booking.budapest.function.v1.reservation.dto.ReservationDetailsDto;
import com.booking.budapest.function.v1.reservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("v1/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public void book(@Valid @RequestBody CreateReservationDto dto) throws Exception {
        reservationService.book(dto.getRoomId(), dto.getUserId(), dto.getFrom(), dto.getTo());
    }

    @PutMapping("/{reservationId}")
    public void cancel(@PathVariable Long reservationId) throws Exception {
        reservationService.cancel(reservationId);
    }

    @GetMapping("/history")
    public List<ReservationDetailsDto> listHistory(
            @RequestParam Long userId,
            @RequestParam(required = false) Integer minPrice,
            @RequestParam(required = false) Integer maxPrice,
            @RequestParam(required = false) Long from,
            @RequestParam(required = false) Long to
    ){
        return reservationService.listHistory(userId, minPrice, maxPrice, from, to);
    }

}

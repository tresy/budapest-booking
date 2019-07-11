package com.booking.budapest.function.v1.reservation.service;

import com.booking.budapest.function.v1.reservation.dto.ReservationDetailsDto;

import java.util.List;

public interface ReservationService {

    void book(long roomId, long userId, long from, long to) throws Exception;
    void cancel(long reservationId) throws Exception;
    List<ReservationDetailsDto> listHistory(long userId);

}

package com.booking.budapest.function.v1.reservation.service;

import com.booking.budapest.entity.AppUser;
import com.booking.budapest.entity.Reservation;
import com.booking.budapest.entity.Room;
import com.booking.budapest.function.v1.reservation.dto.ReservationDetailsDto;
import com.booking.budapest.repository.ReservationRepository;
import com.booking.budapest.repository.RoomRepository;
import com.booking.budapest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void book(long roomId, long userId, long from, long to) throws Exception {
        if (from > to) {
            throw new Exception("From date can not be bigger than the to date!");
        }

        if (reservationRepository.isBooked(roomId, from, to)) {
            throw new Exception(String.format("Room with id: [%d] is booked in the selected range!", roomId));
        }

        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new Exception(String.format("Room doesn't exist with id: [%d]", roomId)));

        AppUser appUser = userRepository.findById(userId)
                .orElseThrow(() -> new Exception(String.format("User doesn't exist with id: [%d]", userId)));

        Reservation reservation = Reservation.builder()
                .room(room)
                .user(appUser)
                .fromDate(from)
                .toDate(to)
                .cancelled(false)
                .build();

        reservationRepository.save(reservation);
    }

    @Override
    public void cancel(long reservationId) throws Exception {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new Exception(String.format("Reservation doesn't exist with id: [%d]", reservationId)));

        reservation.setCancelled(true);
        reservationRepository.save(reservation);
    }

    @Override
    public List<ReservationDetailsDto> listHistory(long userId, Integer minPrice, Integer maxPrice, Long from, Long to) {
        return reservationRepository.findAllByUser_Id(userId)
                .stream()
                .filter(r -> {
                    if (minPrice != null && r.getRoom().getPricePerDayInHuf() < minPrice) {
                        return false;
                    }

                    if (maxPrice != null && r.getRoom().getPricePerDayInHuf() > maxPrice) {
                        return  false;
                    }

                    if (from != null && r.getFromDate() < from) {
                        return false;
                    }

                    if (to != null && r.getToDate() > to) {
                        return false;
                    }

                    return true;
                })
                .map(r ->
                        ReservationDetailsDto.builder()
                                .id(r.getId())
                                .room(r.getRoom().getName())
                                .user(r.getUser().getUsername())
                                .from(r.getFromDate())
                                .to(r.getToDate())
                                .cancelled(r.getCancelled())
                                .pricePerDayInHuf(r.getRoom().getPricePerDayInHuf())
                                .build()
                ).collect(Collectors.toList());
    }
}

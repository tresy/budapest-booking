package com.booking.budapest.function.v1.room.service;

import com.booking.budapest.entity.Room;
import com.booking.budapest.function.v1.room.dto.RoomDetailsDto;
import com.booking.budapest.repository.ReservationRepository;
import com.booking.budapest.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public List<RoomDetailsDto> getAvailableRooms(int minPrice, int maxPrice, long from, long to) {
        List<Room> rooms = roomRepository.findAllByPricePerDayInHufBetween(minPrice, maxPrice);
        return rooms.stream()
                .filter(r -> !reservationRepository.isBooked(r.getId(), from, to))
                .map(r ->
                        RoomDetailsDto.builder()
                                .name(r.getName())
                                .pricePerDayInHuf(r.getPricePerDayInHuf())
                                .build()
                ).collect(Collectors.toList());
    }

}

package com.booking.budapest.function.v1.room.service;

import com.booking.budapest.function.v1.room.dto.RoomDetailsDto;

import java.util.List;

public interface RoomService {

    List<RoomDetailsDto> getAvailableRooms(int minPrice, int maxPrice, long from, long to);

}

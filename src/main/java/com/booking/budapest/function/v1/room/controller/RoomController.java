package com.booking.budapest.function.v1.room.controller;

import com.booking.budapest.function.v1.room.dto.RoomDetailsDto;
import com.booking.budapest.function.v1.room.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping("available")
    public List<RoomDetailsDto> getAvailableRooms(
            @RequestParam int minPrice,
            @RequestParam int maxPrice,
            @RequestParam long from,
            @RequestParam long to
    ) {
        return roomService.getAvailableRooms(minPrice, maxPrice, from, to);
    }

}

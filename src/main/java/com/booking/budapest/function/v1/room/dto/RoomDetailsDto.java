package com.booking.budapest.function.v1.room.dto;


import lombok.*;

@Getter
@Setter
@Builder
public class RoomDetailsDto {

    private String name;
    private Integer pricePerDayInHuf;

}

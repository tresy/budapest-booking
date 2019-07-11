package com.booking.budapest.function.v1.reservation.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
public class CreateReservationDto {

    @NotNull
    private Long roomId;

    @NotNull
    private Long userId;

    @NotNull
    private Long from;

    @NotNull
    private Long to;

}

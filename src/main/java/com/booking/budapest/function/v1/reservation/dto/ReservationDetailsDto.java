package com.booking.budapest.function.v1.reservation.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
public class ReservationDetailsDto {

    private Long id;
    private String room;
    private String user;
    private Long from;
    private Long to;
    private Boolean cancelled;

}

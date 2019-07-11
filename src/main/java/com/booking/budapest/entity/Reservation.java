package com.booking.budapest.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reservation extends MasterEntity {

    @ManyToOne(optional = false)
    private AppUser user;

    @ManyToOne(optional = false)
    private Room room;

    @Column(nullable = false)
    private Long fromDate;

    @Column(nullable = false)
    private Long toDate;

    @Column(nullable = false)
    private Boolean cancelled = false;

}

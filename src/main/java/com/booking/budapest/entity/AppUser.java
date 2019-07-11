package com.booking.budapest.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppUser extends MasterEntity {

    @Column(unique = true, nullable = false)
    private String username;

}

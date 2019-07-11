package com.booking.budapest.repository;

import com.booking.budapest.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findAllByPricePerDayInHufBetween(int min, int max);

}

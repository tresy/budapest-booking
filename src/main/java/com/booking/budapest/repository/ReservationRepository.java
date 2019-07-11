package com.booking.budapest.repository;

import com.booking.budapest.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT CASE WHEN COUNT(1) > 0 THEN true ELSE false END " +
            "FROM Reservation re " +
            "JOIN re.room ro " +
            "JOIN re.user u " +
            "WHERE ro.id = :roomId " +
            "AND (" +
                "(:from BETWEEN re.fromDate AND re.toDate) OR (:to BETWEEN re.fromDate AND re.toDate) " +
                "OR " +
                "(re.fromDate BETWEEN :from AND :to) OR (re.toDate BETWEEN :from AND :to) " +
            ") " +
            "AND re.cancelled IS FALSE")
    boolean isBooked(@Param("roomId") long roomId, @Param("from") Long from, @Param("to") Long to);

    List<Reservation> findAllByUser_Id(long userId);

}

package com.flight.repository;

import com.flight.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    @Query("SELECT f FROM Flight f WHERE " +
           "f.departureAirport = :departure AND " +
           "f.arrivalAirport = :arrival AND " +
           "CAST(f.departureTime AS date) = :date")
    List<Flight> searchFlights(
            @Param("departure") String departure,
            @Param("arrival") String arrival,
            @Param("date") LocalDate date);
}

package com.flight.repository;

import com.flight.dto.FlightDTO;
import com.flight.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    @Query("""
        SELECT new com.flight.dto.FlightDTO(
            f.flightNumber, f.airline, f.departureAirport, f.arrivalAirport,
            dep.name as departureCity, arr.name as arrivalCity,
            f.departureTime, f.arrivalTime,
            f.economyPrice, f.businessPrice,
            f.availableEconomySeats, f.availableBusinessSeats)
        FROM Flight f
        LEFT JOIN City dep ON f.departureAirport = dep.code
        LEFT JOIN City arr ON f.arrivalAirport = arr.code
        WHERE f.departureAirport = :departure
        AND f.arrivalAirport = :arrival
        AND CAST(f.departureTime AS date) = :date
        """)
    List<FlightDTO> searchFlights(
            @Param("departure") String departure,
            @Param("arrival") String arrival,
            @Param("date") LocalDate date);
}

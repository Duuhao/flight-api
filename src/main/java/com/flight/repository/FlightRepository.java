package com.flight.repository;

import com.flight.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    @Query("SELECT f, dep.name as departureCityName, arr.name as arrivalCityName " +
           "FROM Flight f " +
           "LEFT JOIN City dep ON f.departureAirport = dep.code " +
           "LEFT JOIN City arr ON f.arrivalAirport = arr.code " +
           "WHERE f.departureAirport = :departure " +
           "AND f.arrivalAirport = :arrival " +
           "AND CAST(f.departureTime AS date) = :date")
    List<Object[]> searchFlights(
            @Param("departure") String departure,
            @Param("arrival") String arrival,
            @Param("date") LocalDate date);
}

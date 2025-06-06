package com.flight.service;

import com.flight.entity.Flight;
import com.flight.entity.City;
import java.time.LocalDate;
import java.util.List;

public interface FlightService {
    List<Flight> searchFlights(String departure, String arrival, LocalDate date);
    List<City> getAllCities();
}

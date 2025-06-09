package com.flight.service;

import com.flight.dto.FlightDTO;
import com.flight.entity.City;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface FlightService {
    List<FlightDTO> searchFlights(String departure, String arrival, LocalDate date);
    List<City> getAllCities();
    List<Map<String, String>> getAllCitiesAsMap();
}

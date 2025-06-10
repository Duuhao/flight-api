package com.flight.service;

import com.flight.dto.FlightDTO;
import com.flight.dto.FlightSearchRequest;
import com.flight.entity.City;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface FlightService {
    List<FlightDTO> searchFlights(FlightSearchRequest request);
    FlightDTO getFlightDetails(Long id);
    List<Map<String, String>> getAllCityMaps();
    List<City> getAllCities();
}

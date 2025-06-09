package com.flight.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.flight.dto.FlightDTO;
import com.flight.entity.City;
import com.flight.repository.CityRepository;
import com.flight.repository.FlightRepository;
import com.flight.util.FlightMapper;

@Service
public class FlightServiceImpl implements FlightService {
    private final FlightRepository flightRepository;
    private final CityRepository cityRepository;
    private final FlightMapper flightMapper;

    public FlightServiceImpl(FlightRepository flightRepository, 
                           CityRepository cityRepository,
                           FlightMapper flightMapper) {
        this.flightRepository = flightRepository;
        this.cityRepository = cityRepository;
        this.flightMapper = flightMapper;
    }
    
    @Override
    public List<FlightDTO> searchFlights(String departure, String arrival, LocalDate date) {
        return flightRepository.searchFlights(departure, arrival, date);
    }

    @Override
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    @Override
    public List<Map<String, String>> getAllCitiesAsMap() {
        return cityRepository.findAll().stream()
            .map(city -> Map.of(
                "code", city.getCode(),
                "name", city.getName(),
                "country", city.getCountry()
            ))
            .collect(Collectors.toList());
    }
}

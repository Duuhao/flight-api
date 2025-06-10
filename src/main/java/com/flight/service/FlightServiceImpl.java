package com.flight.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.flight.dto.FlightDTO;
import com.flight.dto.FlightSearchRequest;
import com.flight.entity.City;
import com.flight.entity.Flight;
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
    public List<FlightDTO> searchFlights(FlightSearchRequest request) {
        return flightRepository.searchFlights(
            request.getDeparture(),
            request.getArrival(),
            request.getDate()
        );
    }

    public List<FlightDTO> searchFlights(String departure, String arrival, LocalDate date) {
        FlightSearchRequest request = new FlightSearchRequest();
        request.setDeparture(departure);
        request.setArrival(arrival);
        request.setDate(date);
        return searchFlights(request);
    }

    @Override
    public FlightDTO getFlightDetails(Long id) {
        return flightMapper.toDTO(flightRepository.findById(id).orElseThrow());
    }

    @Override
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    @Override
    public List<Map<String, String>> getAllCityMaps() {
        return cityRepository.findAll().stream()
            .map(city -> Map.of(
                "code", city.getCode(),
                "name", city.getName(),
                "country", city.getCountry()
            ))
            .toList();
    }
}

package com.flight.service;

import com.flight.entity.Flight;
import com.flight.repository.FlightRepository;
import com.flight.repository.CityRepository;
import com.flight.entity.City;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {
    private final FlightRepository flightRepository;
    private final CityRepository cityRepository;

    public FlightServiceImpl(FlightRepository flightRepository, CityRepository cityRepository) {
        this.flightRepository = flightRepository;
        this.cityRepository = cityRepository;
    }
    
    @Override
    public List<Flight> searchFlights(String departure, String arrival, LocalDate date) {
        return flightRepository.searchFlights(departure, arrival, date);
    }

    @Override
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }
}

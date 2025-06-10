package com.flight.controller;

import com.flight.dto.FlightDTO;
import com.flight.dto.FlightSearchRequest;
import com.flight.entity.Flight;
import com.flight.service.FlightService;
import com.flight.util.FlightMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    @GetMapping("/cities")
    public ResponseEntity<List<Map<String, String>>> getAllCities() {
        List<Map<String, String>> cities = flightService.getAllCities()
            .stream()
            .map(city -> Map.of(
                "code", city.getCode(),
                "name", city.getName(),
                "country", city.getCountry()
            ))
            .toList();
        return ResponseEntity.ok(cities);
    }
    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/search")
    public ResponseEntity<List<FlightDTO>> searchFlights(
            @RequestParam String departure,
            @RequestParam String arrival,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        FlightSearchRequest request = new FlightSearchRequest();
        request.setDeparture(departure);
        request.setArrival(arrival);
        request.setDate(date);
        return ResponseEntity.ok(flightService.searchFlights(request));
    }
}

package com.flight.util;

import com.flight.dto.FlightDTO;
import com.flight.entity.Flight;

public class FlightMapper {
    public static FlightDTO toDTO(Flight flight) {
        FlightDTO dto = new FlightDTO();
        dto.setFlightNumber(flight.getFlightNumber());
        dto.setAirline(flight.getAirline());
        dto.setDepartureAirport(flight.getDepartureAirport());
        dto.setArrivalAirport(flight.getArrivalAirport());
        dto.setDepartureTime(flight.getDepartureTime());
        dto.setArrivalTime(flight.getArrivalTime());
        dto.setEconomyPrice(flight.getEconomyPrice());
        dto.setBusinessPrice(flight.getBusinessPrice());
        dto.setAvailableEconomySeats(flight.getAvailableEconomySeats());
        dto.setAvailableBusinessSeats(flight.getAvailableBusinessSeats());
        return dto;
    }
}

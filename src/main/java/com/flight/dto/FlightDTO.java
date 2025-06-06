package com.flight.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class FlightDTO {
    private String flightNumber;
    private String airline;
    private String departureAirport;
    private String arrivalAirport;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private Double economyPrice;
    private Double businessPrice;
    private Integer availableEconomySeats;
    private Integer availableBusinessSeats;
}

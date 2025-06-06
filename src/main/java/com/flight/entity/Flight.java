package com.flight.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "flights")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String flightNumber;
    private String airline;
    private String departureAirport;
    private String arrivalAirport;
    
    @Transient
    private City departureCity;
    
    @Transient
    private City arrivalCity;
    
    @Transient
    private String departureCityName;
    
    @Transient
    private String arrivalCityName;
    
    public String getDepartureCityName() {
        return departureCityName;
    }
    
    public void setDepartureCityName(String departureCityName) {
        this.departureCityName = departureCityName;
    }
    
    public String getArrivalCityName() {
        return arrivalCityName;
    }
    
    public void setArrivalCityName(String arrivalCityName) {
        this.arrivalCityName = arrivalCityName;
    }
    
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private Double economyPrice;
    private Double businessPrice;
    private Integer availableEconomySeats;
    private Integer availableBusinessSeats;
}

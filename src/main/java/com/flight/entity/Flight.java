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
    
    @Column(name = "flight_number")
    private String flightNumber;
    
    @Column(name = "airline")
    private String airline;
    
    @Column(name = "departure_airport")
    private String departureAirport;
    
    @Column(name = "arrival_airport")
    private String arrivalAirport;
    
    @ManyToOne
    @JoinColumn(name = "departure_airport", referencedColumnName = "code", insertable = false, updatable = false)
    private City departureCity;
    
    @ManyToOne
    @JoinColumn(name = "arrival_airport", referencedColumnName = "code", insertable = false, updatable = false)
    private City arrivalCity;
    
    @Column(name = "departure_city_name")
    private String departureCityName;
    
    @Column(name = "arrival_city_name")
    private String arrivalCityName;
    
    @Column(name = "departure_time")
    private LocalDateTime departureTime;
    
    @Column(name = "arrival_time")
    private LocalDateTime arrivalTime;
    
    @Column(name = "economy_price")
    private Double economyPrice;
    
    @Column(name = "business_price")
    private Double businessPrice;
    
    @Column(name = "available_economy_seats")
    private Integer availableEconomySeats;
    
    @Column(name = "available_business_seats")
    private Integer availableBusinessSeats;
}

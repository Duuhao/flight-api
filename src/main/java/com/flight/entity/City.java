package com.flight.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Entity
@Table(name = "cities")
@Data
@Getter
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true) 
    private String code;

    private String name;  

    private String country;  

    private String timezone; 

    private Boolean isActive = true;  
}

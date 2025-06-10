package com.flight.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Membership {
    @Id
    private Integer id;
    private String name;
    private String description;
}

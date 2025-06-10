package com.flight.util;

import com.flight.dto.FlightDTO;
import com.flight.entity.City;
import com.flight.entity.Flight;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface FlightMapper {
    FlightMapper INSTANCE = Mappers.getMapper(FlightMapper.class);
    
    @Mapping(target = "departureCity", source = "departureCityName")
    @Mapping(target = "arrivalCity", source = "arrivalCityName")
    FlightDTO toDTO(Flight flight);
    
    @Mapping(target = "departureCity", ignore = true)
    @Mapping(target = "arrivalCity", ignore = true)
    FlightDTO toSimpleDTO(Flight flight);
}

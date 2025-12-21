package com.thy.airlineroutemanager.mapper;

import com.thy.airlineroutemanager.dto.TransportationDto;
import com.thy.airlineroutemanager.entity.Transportation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import org.springframework.data.domain.Page;

import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TransportationMapper {

    @Mapping(target = "operatingDays", qualifiedByName = "convertIntArrayToDayOfWeekList")
    @Mapping(target = "originLocation.id", source = "originLocation")
    @Mapping(target = "destinationLocation.id", source = "destinationLocation")
    TransportationDto toDto(Transportation transportation);

    @Mapping(target = "originLocation", source = "originLocation.id")
    @Mapping(target = "destinationLocation", source = "destinationLocation.id")
    @Mapping(target = "operatingDays", qualifiedByName = "convertOperatingDaysToIntArray")
    Transportation toEntity(TransportationDto transportationDto);

    List<TransportationDto> toDtoList(List<Transportation> transportation);

    List<Transportation> toEntityList(List<TransportationDto> transportationDto);

    @Named("convertOperatingDaysToIntArray")
    default Integer[] convertOperatingDaysToIntArray(List<DayOfWeek> operatingDays) {
        if (operatingDays == null) {
            return null;
        }
        return operatingDays.stream().map(DayOfWeek::getValue).toArray(Integer[]::new);
    }

    @Named("convertIntArrayToDayOfWeekList")
    default List<DayOfWeek> convertIntArrayToDayOfWeekList(Integer[] intArray) {
        if (intArray == null) {
            return null;
        }
        return Arrays.stream(intArray).map(DayOfWeek::of).toList();
    }
}

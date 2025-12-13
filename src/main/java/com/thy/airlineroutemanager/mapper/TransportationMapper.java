package com.thy.airlineroutemanager.mapper;

import com.thy.airlineroutemanager.dto.TransportationDto;
import com.thy.airlineroutemanager.entity.Transportation;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TransportationMapper {

    TransportationDto toDto(Transportation transportation);

    Transportation toEntity(TransportationDto transportationDto);
}

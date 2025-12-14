package com.thy.airlineroutemanager.dto;

import com.thy.airlineroutemanager.entity.Transportation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RouteDto {

    private List<Transportation> transportations;
}

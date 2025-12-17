package com.thy.airlineroutemanager.response;

import com.thy.airlineroutemanager.dto.RouteDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class RouteResponse {

    private List<RouteDto> routes;
}

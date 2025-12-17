package com.thy.airlineroutemanager.domain;

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
public class Route {

    private List<Transportation> transportations;
}

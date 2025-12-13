package com.thy.airlineroutemanager.entity;

import com.thy.airlineroutemanager.enums.TransportationType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Table(name = "transportation")
@Entity
public class Transportation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transportation_seq")
    @SequenceGenerator(name = "transportation_seq", sequenceName = "seq_transportation")
    private Long id;

    private String originLocation;

    private String destinationLocation;

    private TransportationType transportationType;

    private String operatingDays;
}

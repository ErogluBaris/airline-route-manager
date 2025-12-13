package com.thy.airlineroutemanager.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Table(name = "location")
@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "location_seq")
    @SequenceGenerator(name = "location_seq", sequenceName = "seq_location")
    private Long id;

    private String name;

    private String country;

    private String city;

    private String locationCode;
}

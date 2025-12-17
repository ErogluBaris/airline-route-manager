package com.thy.airlineroutemanager.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Table(name = "location",
        indexes = {
                @Index(name = "idx_location_name", columnList = "name"),
                @Index(name = "idx_location_code", columnList = "location_code"),
                @Index(name = "idx_location_country_city", columnList = "country, city")
        })
@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "location_seq")
    @SequenceGenerator(name = "location_seq", sequenceName = "seq_location", allocationSize = 1)
    private Long id;

    @Version
    @Column(name = "version")
    private Integer version;

    @Column(name = "name")
    private String name;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "locationCode")
    private String locationCode;
}

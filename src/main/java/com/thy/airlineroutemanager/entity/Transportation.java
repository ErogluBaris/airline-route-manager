package com.thy.airlineroutemanager.entity;

import com.thy.airlineroutemanager.enums.TransportationType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Setter
@Getter
@Table(name = "transportation")
@Entity
public class Transportation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transportation_seq")
    @SequenceGenerator(name = "transportation_seq", sequenceName = "seq_transportation")
    @Column(name = "id")
    private Long id;

    @Version
    private Integer version;

    //@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //@JoinColumn(name = "origin_location_id")
    @Column(name = "origin_location")
    private Long originLocation;

    //@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //@JoinColumn(name = "destination_location_id")
    @Column(name = "destination_location")
    private Long destinationLocation;

    @Enumerated(EnumType.STRING)
    @Column(name = "transportation_type")
    private TransportationType transportationType;

    @JdbcTypeCode(SqlTypes.ARRAY)
    @Column(name = "operating_days", columnDefinition = "int[]")
    private Integer[] operatingDays;
}

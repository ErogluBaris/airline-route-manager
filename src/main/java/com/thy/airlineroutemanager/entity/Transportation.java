package com.thy.airlineroutemanager.entity;

import com.thy.airlineroutemanager.enums.TransportationType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Setter
@Getter
@Table(name = "transportation",
        indexes = {
                @Index(name = "idx_transportation_origin", columnList = "origin_location"),
                @Index(name = "idx_transportation_destination", columnList = "destination_location"),
                @Index(name = "idx_transportation_type", columnList = "transportation_type"),
                @Index(name = "idx_transportation_origin_type", columnList = "origin_location, transportation_type")
        })
@Entity
public class Transportation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transportation_seq")
    @SequenceGenerator(name = "transportation_seq", sequenceName = "seq_transportation", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Version
    @Column(name = "version")
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

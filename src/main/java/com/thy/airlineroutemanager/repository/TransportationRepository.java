package com.thy.airlineroutemanager.repository;

import com.thy.airlineroutemanager.entity.Transportation;
import com.thy.airlineroutemanager.enums.TransportationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface TransportationRepository extends JpaRepository<Transportation, Long> {

    @Query(value = "select * from transportation where (origin_location = :originLocationId or destination_location = :destinationLocationId) and :operatingDay = any (operating_days)",
            nativeQuery = true)
    List<Transportation> findAllByOriginLocationOrDestinationLocation(@Param("originLocationId") Long originLocationId,
                                                                      @Param("destinationLocationId") Long destinationLocationId,
                                                                      @Param("operatingDay") Integer operatingDay);

    @Query(value = "select * from transportation where origin_location in (:originLocationIds) and destination_location in (:destinationLocationIds) and transportation_type= :transportationType and :operatingDay = any (operating_days)",
            nativeQuery = true)
    List<Transportation> findAllByOriginLocationInAndDestinationLocationInAndTransportationTypeAndOperatingDay(@Param("originLocationIds") Set<Long> originLocationIds,
                                                                                                @Param("destinationLocationIds") Set<Long> destinationLocationIds,
                                                                                                @Param("transportationType") String transportationType,
                                                                                                @Param("operatingDay") Integer operatingDay);
}

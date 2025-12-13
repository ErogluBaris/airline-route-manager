package com.thy.airlineroutemanager.repository;

import com.thy.airlineroutemanager.entity.Transportation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransportationRepository extends JpaRepository<Transportation, Long> {
}

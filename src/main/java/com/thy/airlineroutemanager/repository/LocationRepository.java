package com.thy.airlineroutemanager.repository;

import com.thy.airlineroutemanager.entity.Location;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    @Query(value = """
        SELECT DISTINCT l.*
        FROM location l
        WHERE l.name ILIKE %:nameLike% or location_code ILIKE %:nameLike%
    """, nativeQuery = true)
    List<Location> findAllByNameLike(@Param("nameLike") String nameLike, Pageable pageable);

    List<Location> findAllBy(Pageable pageable);
}

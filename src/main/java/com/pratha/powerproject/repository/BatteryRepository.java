package com.pratha.powerproject.repository;

import com.pratha.powerproject.entity.Battery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <b>The interface Battery repository</b>
 *
 * @author Pramosh Shrestha
 * @created 01 /11/2023: 19:30
 */
@Repository
public interface BatteryRepository extends JpaRepository<Battery, Long> {

    /**
     * <b>Finds the list of the batteries between the provided postcode range and orders by name.</b>
     *
     * <p>The query is build via the JPQL from the Spring JPA framework</p>
     *
     * @param minPostCode {@link String}: the min post code
     * @param maxPostCode {@link String}: the max post code
     * @return List of {@link Battery}
     */
    List<Battery> findBatteriesByPostcodeBetweenOrderByName(String minPostCode, String maxPostCode);
}

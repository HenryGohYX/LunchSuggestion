package com.gov.tech.repository;

import com.gov.tech.model.LunchLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LunchLocationRepository extends JpaRepository<LunchLocation, Long> {

}

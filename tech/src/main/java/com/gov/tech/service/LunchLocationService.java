package com.gov.tech.service;

import com.gov.tech.dto.LunchLocationRequest;
import com.gov.tech.dto.LunchLocationResponse;

public interface LunchLocationService {

    LunchLocationResponse save(LunchLocationRequest lunchLocationRequest);

    LunchLocationResponse allLunchLocation();

    LunchLocationResponse generateRandomLocation();

    LunchLocationResponse getLunchLocationById(Long id);

    void deleteLunchLocationById(Long id);
}

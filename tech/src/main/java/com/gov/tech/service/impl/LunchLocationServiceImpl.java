package com.gov.tech.service.impl;

import com.gov.tech.dto.LunchLocationRequest;
import com.gov.tech.dto.LunchLocationResponse;
import com.gov.tech.exception.ResourceNotFoundException;
import com.gov.tech.model.LunchLocation;
import com.gov.tech.repository.LunchLocationRepository;
import com.gov.tech.service.LunchLocationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Slf4j
@Service
public class LunchLocationServiceImpl implements LunchLocationService {

    @Autowired
    LunchLocationRepository lunchLocationRepository;

    private static final Random random = new Random();

    @Override
    public LunchLocationResponse save(LunchLocationRequest lunchLocationRequest) {
        LunchLocation lunchLocation = new LunchLocation(lunchLocationRequest.getName(), lunchLocationRequest.getLocation());

        if (lunchLocationRequest.getId() != null) {
            log.debug("updating: {}", lunchLocationRequest);
            lunchLocation.setId(lunchLocationRequest.getId());
        } else {
            log.debug("inserting: {}", lunchLocationRequest);
        }

        return LunchLocationResponse.builder()
                .lunchLocation(lunchLocationRepository.save(lunchLocation))
                .status(1)
                .build();
    }

    @Override
    public LunchLocationResponse allLunchLocation() {
        return LunchLocationResponse.builder()
                .lunchLocationList(lunchLocationRepository.findAll())
                .status(1)
                .build();
    }

    @Override
    public LunchLocationResponse generateRandomLocation() {
        List<LunchLocation> lunchLocationList = lunchLocationRepository.findAll();

        if (lunchLocationList.isEmpty()) {
            log.info("lunch location list is empty");
            return LunchLocationResponse.builder()
                    .lunchLocationList(lunchLocationList)
                    .status(1)
                    .build();
        }

        return getRandomLocation(lunchLocationList);
    }

    public static LunchLocationResponse getRandomLocation(List<LunchLocation> lunchLocationList) {
        int randomIndex = random.nextInt(lunchLocationList.size());
        log.debug("random idx generated: {}", randomIndex); // Analyzing random
        return LunchLocationResponse.builder()
                .lunchLocation(lunchLocationList.get(randomIndex))
                .status(1)
                .build();
    }

    @Override
    public LunchLocationResponse getLunchLocationById(Long id) {
        return LunchLocationResponse.builder()
                .lunchLocation(lunchLocationRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("LunchLocation not found for id: " + id)))
                .status(1)
                .build();
    }

    @Override
    public void deleteLunchLocationById(Long id) {
        if (!lunchLocationRepository.existsById(id)) {
            throw new ResourceNotFoundException("LunchLocation not found for id: " + id);
        }

        lunchLocationRepository.deleteById(id);
    }
}

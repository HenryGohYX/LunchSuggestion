package com.gov.tech.util;

import com.gov.tech.model.LunchLocation;
import com.gov.tech.repository.LunchLocationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LoadData implements CommandLineRunner {

    @Autowired
    private LunchLocationRepository lunchLocationRepository;

    @Override
    public void run(String... args) throws Exception {

        LunchLocation defaultLunchLocation = new LunchLocation("Default", "Subway");
        lunchLocationRepository.save(defaultLunchLocation);
        lunchLocationRepository.findAll().forEach(lunchLocation -> log.info("Initialized Lunch Location: {}", lunchLocation));
    }
}

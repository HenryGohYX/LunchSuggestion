package com.gov.tech.service.impl;

import com.gov.tech.dto.LunchLocationRequest;
import com.gov.tech.dto.LunchLocationResponse;
import com.gov.tech.exception.ResourceNotFoundException;
import com.gov.tech.model.LunchLocation;
import com.gov.tech.repository.LunchLocationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LunchLocationServiceImplTest {

    @InjectMocks
    private LunchLocationServiceImpl lunchLocationServiceImpl;

    @Mock
    private LunchLocationRepository lunchLocationRepository;

    @Test
    void testSave_update() {
        LunchLocationRequest request = new LunchLocationRequest();
        request.setId(1L);
        request.setName("Nick");
        request.setLocation("Burger King");
        LunchLocation savedLunchLocation = new LunchLocation("Steve", "Long John Silver's");

        when(lunchLocationRepository.save(any(LunchLocation.class))).thenReturn(savedLunchLocation);
        LunchLocationResponse response = lunchLocationServiceImpl.save(request);

        assertEquals(1, response.getStatus());
        assertEquals(savedLunchLocation, response.getLunchLocation());
    }

    @Test
    void testSave_insert() {
        LunchLocationRequest request = new LunchLocationRequest();
        request.setName("Nick");
        request.setLocation("Burger King");
        LunchLocation savedLunchLocation = new LunchLocation("Steve", "Long John Silver's");

        when(lunchLocationRepository.save(any(LunchLocation.class))).thenReturn(savedLunchLocation);
        LunchLocationResponse response = lunchLocationServiceImpl.save(request);

        assertEquals(1, response.getStatus());
        assertEquals(savedLunchLocation, response.getLunchLocation());
    }

    @Test
    void testAllLunchLocation() {
        List<LunchLocation> lunchLocationList = Collections.singletonList(new LunchLocation("Peter", "Mos Burger"));

        when(lunchLocationRepository.findAll()).thenReturn(lunchLocationList);
        LunchLocationResponse response = lunchLocationServiceImpl.allLunchLocation();

        assertEquals(1, response.getStatus());
        assertEquals(lunchLocationList, response.getLunchLocationList());
    }

    @Test
    void testGenerateRandomLocation() {
        List<LunchLocation> lunchLocationList = Collections.singletonList(new LunchLocation("Peter", "Mos Burger"));

        when(lunchLocationRepository.findAll()).thenReturn(lunchLocationList);
        LunchLocationResponse response = lunchLocationServiceImpl.generateRandomLocation();

        assertEquals(1, response.getStatus());
        assertEquals("Peter", response.getLunchLocation().getName());
        assertEquals("Mos Burger", response.getLunchLocation().getLocation());
    }

    @Test
    void testGenerateRandomLocation_emptyLunchLocationList() {
        List<LunchLocation> lunchLocationList = new ArrayList<>();

        when(lunchLocationRepository.findAll()).thenReturn(lunchLocationList);
        LunchLocationResponse response = lunchLocationServiceImpl.generateRandomLocation();

        assertEquals(1, response.getStatus());
    }

    @Test
    void testGetLunchLocationById() {
        Long id = 1L;
        LunchLocation lunchLocation = new LunchLocation("Ace", "Texas Chicken");

        when(lunchLocationRepository.findById(id)).thenReturn(Optional.of(lunchLocation));
        LunchLocationResponse response = lunchLocationServiceImpl.getLunchLocationById(id);

        assertEquals(1, response.getStatus());
        assertEquals(lunchLocation, response.getLunchLocation());
    }

    @Test
    void testDeleteLunchLocationById() {
        Long id = 1L;

        when(lunchLocationRepository.existsById(id)).thenReturn(true);
        lunchLocationServiceImpl.deleteLunchLocationById(id);

        verify(lunchLocationRepository, times(1)).deleteById(id);
    }

    @Test
    void testDeleteLunchLocationById_resourceNotFound() {
        Long id = 1L;

        when(lunchLocationRepository.existsById(id)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> {
            lunchLocationServiceImpl.deleteLunchLocationById(id);
        });
    }
}

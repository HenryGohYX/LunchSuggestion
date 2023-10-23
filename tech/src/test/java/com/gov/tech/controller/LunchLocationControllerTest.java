package com.gov.tech.controller;

import com.gov.tech.dto.LunchLocationRequest;
import com.gov.tech.dto.LunchLocationResponse;
import com.gov.tech.service.LunchLocationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LunchLocationControllerTest {

    @InjectMocks
    private LunchLocationController lunchLocationController;

    @Mock
    private LunchLocationService lunchLocationService;

    @Test
    void testCreateSuggestion() {
        LunchLocationRequest request = new LunchLocationRequest();
        request.setName("James");
        request.setLocation("KFC");
        LunchLocationResponse response = LunchLocationResponse.builder()
                .status(1)
                .build();

        when(lunchLocationService.save(request)).thenReturn(response);
        LunchLocationResponse result = lunchLocationController.createSuggestion(request);

        assertEquals(response.getStatus(), result.getStatus());
    }

    @Test
    void testUpdateSuggestion() {
        LunchLocationRequest request = new LunchLocationRequest();
        request.setId(1L);
        request.setName("James");
        request.setLocation("Mac");
        LunchLocationResponse response = LunchLocationResponse.builder()
                .status(1)
                .build();

        when(lunchLocationService.save(request)).thenReturn(response);
        LunchLocationResponse result = lunchLocationController.updateSuggestion(request);

        assertEquals(response.getStatus(), result.getStatus());
    }

    @Test
    void testGetAllSuggestions() {
        LunchLocationResponse response = LunchLocationResponse.builder()
                .status(1)
                .build();

        when(lunchLocationService.allLunchLocation()).thenReturn(response);
        LunchLocationResponse result = lunchLocationController.lunchLocations();

        assertEquals(response.getStatus(), result.getStatus());
    }

    @Test
    void testGetRandomSuggestion() {
        LunchLocationResponse response = LunchLocationResponse.builder()
                .status(1)
                .build();

        when(lunchLocationService.generateRandomLocation()).thenReturn(response);
        LunchLocationResponse result = lunchLocationController.randomlunchLocation();

        assertEquals(response.getStatus(), result.getStatus());
    }

    @Test
    void testGetSuggestionById() {
        Long id = 1L;
        LunchLocationResponse response = LunchLocationResponse.builder()
                .status(1)
                .build();

        when(lunchLocationService.getLunchLocationById(id)).thenReturn(response);
        LunchLocationResponse result = lunchLocationController.lunchLocation(id);

        assertEquals(response.getStatus(), result.getStatus());
    }

    @Test
    void testDeleteSuggestion() {
        Long id = 1L;
        lunchLocationController.deleteLunchLocation(id);

        verify(lunchLocationService).deleteLunchLocationById(id);
    }
}

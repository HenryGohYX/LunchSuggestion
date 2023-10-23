package com.gov.tech.controller;

import com.gov.tech.dto.LunchLocationRequest;
import com.gov.tech.dto.LunchLocationResponse;
import com.gov.tech.service.LunchLocationService;
import com.gov.tech.util.UpdateValidation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/lunch")
public class LunchLocationController {

    @Autowired
    private LunchLocationService lunchLocationService;

    @Operation(summary = "Create a suggestion for lunch location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created the suggestion for lunch location",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = LunchLocationResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid name, location or both supplied",
                    content = @Content)})
    @PostMapping("/suggestion")
    @ResponseStatus(HttpStatus.CREATED)
    LunchLocationResponse createSuggestion(@Valid @RequestBody LunchLocationRequest lunchLocationRequest) {
        log.info("request to add lunch suggestion: {} by {}", lunchLocationRequest.getLocation(), lunchLocationRequest.getName());
        return lunchLocationService.save(lunchLocationRequest);
    }

    @Operation(summary = "Update a suggestion for lunch location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated the suggestion for lunch location",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = LunchLocationResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id, name, location or combination supplied",
                    content = @Content)})
    @PutMapping("/suggestion/{id}")
    @ResponseStatus(HttpStatus.OK)
    LunchLocationResponse updateSuggestion(@Validated(UpdateValidation.class) @Valid @RequestBody LunchLocationRequest lunchLocationReq) {
        log.info("request to update lunch suggestion: {} by {} for id: {}", lunchLocationReq.getLocation(), lunchLocationReq.getName(), lunchLocationReq.getId());
        return lunchLocationService.save(lunchLocationReq);
    }

    @Operation(summary = "Get all suggestions for lunch location")
    @ApiResponse(responseCode = "200", description = "Retrieved all suggestions for lunch location")
    @GetMapping("/suggestions")
    LunchLocationResponse lunchLocations() {
        log.info("request to retrieve all lunch suggestions");
        return lunchLocationService.allLunchLocation();
    }

    @Operation(summary = "Get random suggestion for lunch location")
    @ApiResponse(responseCode = "200", description = "Retrieved random suggestion for lunch location")
    @GetMapping("/suggestion/random")
    LunchLocationResponse randomlunchLocation() {
        log.info("request to retrieve random lunch suggestion");
        return lunchLocationService.generateRandomLocation();
    }

    @Operation(summary = "Get a suggestion for lunch location by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Retrieved a suggestion by its id for lunch location",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Suggestion not found for lunch location",
                    content = @Content)})
    @GetMapping("/suggestion/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    LunchLocationResponse lunchLocation(@PathVariable Long id) {
        log.info("request to retrieve lunch suggestion for id: {}", id);
        return lunchLocationService.getLunchLocationById(id);
    }

    @Operation(summary = "Delete a suggestion for lunch location by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deleted a suggestion by its id for lunch location",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Suggestion not found for lunch location",
                    content = @Content)})
    @DeleteMapping("/suggestion/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLunchLocation(@PathVariable Long id) {
        log.info("request to delete lunch suggestion for id: {}", id);
        lunchLocationService.deleteLunchLocationById(id);
    }
}

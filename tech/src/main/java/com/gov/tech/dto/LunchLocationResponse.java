package com.gov.tech.dto;

import com.gov.tech.model.LunchLocation;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class LunchLocationResponse {

    LunchLocation lunchLocation;

    List<LunchLocation> lunchLocationList;

    @NotBlank
    int status;

    String errorMsg;
}

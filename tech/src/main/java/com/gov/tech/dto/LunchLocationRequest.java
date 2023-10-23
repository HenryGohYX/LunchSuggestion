package com.gov.tech.dto;

import com.gov.tech.util.UpdateValidation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class LunchLocationRequest {

    @NotNull(message = "invalid id", groups = UpdateValidation.class)
    private Long id;

    @NotBlank(message = "invalid name")
    private String name;

    @NotBlank(message = "invalid location")
    private String location;
}

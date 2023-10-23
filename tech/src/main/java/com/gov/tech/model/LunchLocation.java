package com.gov.tech.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class LunchLocation {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String location;
}

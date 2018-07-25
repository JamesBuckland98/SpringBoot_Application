package com.nsa.cm6213.example.charity2018.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Charity {

    private Long id;
    private String name;
    private String acronym;
    private String description;
    private String logoPath;
    private String registrationNumber;
    private boolean isActive;
}

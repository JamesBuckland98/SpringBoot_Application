package com.nsa.cm6213.example.charity2018.domain;

import lombok.Data;

@Data
public class Donor {

    private Long id;
    private String firstName;
    private String lastName;
    private Address address;

}

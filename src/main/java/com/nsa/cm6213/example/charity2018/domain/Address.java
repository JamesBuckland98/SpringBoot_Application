package com.nsa.cm6213.example.charity2018.domain;


import lombok.Data;

@Data
public class Address {
    private Long id;
    private String buildingName;
    private String buildingNumber;
    private String street;
    private String district;
    private String city;
    private String postCode;
    private String countryCode;
}

package com.nsa.cm6213.example.charity2018.controllers;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DonorForm {

    private String name;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String postcode;
    private Double donationAmount;
    private Boolean isGiftAidEligible=Boolean.FALSE;

}

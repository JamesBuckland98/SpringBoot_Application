package com.nsa.cm6213.example.charity2018.controllers;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentForm {

    private String cardNumber;
    private Boolean isCardAddressHomeAddress=Boolean.FALSE;
}

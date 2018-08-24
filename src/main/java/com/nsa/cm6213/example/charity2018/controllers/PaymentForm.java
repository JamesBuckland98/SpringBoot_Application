package com.nsa.cm6213.example.charity2018.controllers;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.CreditCardNumber;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentForm {

    @CreditCardNumber(message = "Invalid Credit Card Number")
    private String cardNumber;
    private Boolean isCardAddressHomeAddress=Boolean.FALSE;
}

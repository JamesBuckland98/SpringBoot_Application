package com.nsa.cm6213.example.charity2018.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Donation {

    private Long id;
    private Long amountInPence;
    private LocalDateTime donationDate;
    private Boolean isOwnMoney;
    private Boolean hasNoBenefitToDonor;
    private Boolean wishesToGiftAid;
    private Donor donor;
    private Charity charity;

}

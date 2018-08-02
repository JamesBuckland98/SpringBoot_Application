package com.nsa.cm6213.example.charity2018.services;

import com.nsa.cm6213.example.charity2018.controllers.DonorForm;
import com.nsa.cm6213.example.charity2018.controllers.PaymentForm;
import com.nsa.cm6213.example.charity2018.domain.Donation;
import org.springframework.stereotype.Service;

public interface DonationService {

    public void createDonation(Donation aDonation);
}

package com.nsa.cm6213.example.charity2018.services;

import com.nsa.cm6213.example.charity2018.controllers.DonorForm;
import com.nsa.cm6213.example.charity2018.controllers.PaymentForm;
import com.nsa.cm6213.example.charity2018.domain.Donation;
import com.nsa.cm6213.example.charity2018.repositories.DonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DonationServiceImpl implements DonationService {

    private DonationRepository donationRepository;

    @Autowired
    public DonationServiceImpl(DonationRepository aRepo) {
        donationRepository = aRepo;
    }

    @Override
    public void createDonation(Donation aDonation) {
        donationRepository.save(aDonation);
    }
}

package com.nsa.cm6213.example.charity2018.repositories;

import com.nsa.cm6213.example.charity2018.domain.Donation;
import org.springframework.stereotype.Repository;

public interface DonationRepository {

    public void save(Donation aDonation);

}

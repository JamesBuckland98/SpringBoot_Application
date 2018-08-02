package com.nsa.cm6213.example.charity2018.repositories;

import com.nsa.cm6213.example.charity2018.domain.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DonationRepositoryJPA extends JpaRepository<Donation, Long>, DonationRepository {

    @Query(value = "select max(id) from donation", nativeQuery = true)
    public Long findLastDonationId();


}

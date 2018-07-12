package com.nsa.cm6213.example.charity2018.services;

import com.nsa.cm6213.example.charity2018.domain.Charity;

import java.util.List;

public interface CharityService {

    public List<Charity> findCharities(String searchTerm);

}
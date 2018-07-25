package com.nsa.cm6213.example.charity2018.repositories;


import com.nsa.cm6213.example.charity2018.domain.Charity;

import java.util.List;
import java.util.Optional;

public interface CharityRepository {

    public Optional<Charity> findOne(Long id);

    public List<Charity> findBySearchTerm(String name);

    public List<Charity> findByName(String name);

    public List<Charity> findAll();


}

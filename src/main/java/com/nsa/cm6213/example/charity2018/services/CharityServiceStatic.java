package com.nsa.cm6213.example.charity2018.services;

import com.nsa.cm6213.example.charity2018.controllers.exceptions.MissingResourceException;
import com.nsa.cm6213.example.charity2018.domain.Charity;
import com.nsa.cm6213.example.charity2018.repositories.CharityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CharityServiceStatic implements CharityService {

    //private static CharityServiceStatic theInstance;
    private List<Charity> theCharities;
    private CharityRepository charityRepository;

    @Autowired
    public CharityServiceStatic(CharityRepository aRepo) {

        charityRepository = aRepo;
    }

    public List<Charity> findCharities(String searchTerm) {

        return charityRepository.findBySearchTerm(searchTerm);
    }


    public Charity findById(Long id) {

        Optional<Charity> charity = charityRepository.findOne(id);

        if (charity.isPresent()) {
            return charity.get();
        } else {
            throw new MissingResourceException("No charity with id" + id);
        }
    }


}

package com.nsa.cm6213.example.charity2018.services;

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
        theCharities = new ArrayList<Charity>();

        theCharities.add(new Charity(1L,"British Heart Foundation", "BHF", "Heart charity", "bhf", "34567890", true));
        theCharities.add(new Charity(2L, "Tenovus", "", "Cancer charity", "tenovus", "56789012", true));
        theCharities.add(new Charity(3L,"Oxfam", "", "Famine charity", "oxfam", "98765432", true));
    }

    public List<Charity> findCharities(String searchTerm){

        return charityRepository.findBySearchTerm(searchTerm);

//        return theCharities
//                .stream()
//                .filter(c -> c.getName().equals(searchTerm))
//                .collect(Collectors.toList());
    }

    public Optional<Charity> findByRegistrationNumber(String regNo) {
        return charityRepository.findByRegistrationNumber(regNo);
    }


}

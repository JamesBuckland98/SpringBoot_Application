package com.nsa.cm6213.example.charity2018.services;

import com.nsa.cm6213.example.charity2018.domain.Charity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CharityServiceStatic implements CharityService {

    //private static CharityServiceStatic theInstance;
    private List<Charity> theCharities;

    public CharityServiceStatic() {

        theCharities = new ArrayList<Charity>();
        theCharities.add(new Charity("BHF","Heart charity","bhf", "34567890", true));
        theCharities.add(new Charity("Tenovus","Cancer charity","tenovus", "56789012", true));
        theCharities.add(new Charity("Oxfam","Famine charity","oxfam", "98765432", true));
    }

    public List<Charity> findCharities(String searchTerm){
        return theCharities
                .stream()
                .filter(c -> c.getName().equals(searchTerm))
                .collect(Collectors.toList());
    }

    public Optional<Charity> findByRegistrationNumber(String regNo) {
        return theCharities
                .stream()
                .filter(c -> c.getRegistrationNumber().equals(regNo))
                .findFirst();
    }


}

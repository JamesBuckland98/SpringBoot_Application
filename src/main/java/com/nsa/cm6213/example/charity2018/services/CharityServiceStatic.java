package com.nsa.cm6213.example.charity2018.services;

import com.nsa.cm6213.example.charity2018.domain.Charity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CharityServiceStatic implements CharityService {

    private static CharityServiceStatic theInstance;
    private List<Charity> theCharities;

    public static CharityService getInstance(){
        if (theInstance == null){
            theInstance = new CharityServiceStatic();
        }
        return theInstance;
    }

    private CharityServiceStatic(){

        theCharities = new ArrayList<Charity>();
        theCharities.add(new Charity("NSPCC","Kids charity","nspcc", "12345678", true));
        theCharities.add(new Charity("RSPCA","Animal charity","rspca", "23456789", true));
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

package com.nsa.cm6213.example.charity2018.controllers;


import com.nsa.cm6213.example.charity2018.controllers.exceptions.MissingResourceException;
import com.nsa.cm6213.example.charity2018.controllers.exceptions.NonUniqueResourceException;
import com.nsa.cm6213.example.charity2018.domain.Charity;
import com.nsa.cm6213.example.charity2018.services.CharityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class CharityProfile {

    private CharityService charityService;

    @Autowired
    public CharityProfile(CharityService aService) {
        charityService = aService;
    }


    @RequestMapping(path = "/charity/{id}", method = RequestMethod.GET)
    public String getCharityProfile(@PathVariable Long id, Model model) {


        Optional<Charity> charity = charityService.findById(id);

        System.out.println(charity.get());

        if (charity.isPresent()) {
            model.addAttribute("charity", charity.get());
        } else {
            throw new MissingResourceException("No such charity", "/findCharity");
        }


        return "CharityProfile";


    }

    @RequestMapping(path = "/charity", method = RequestMethod.GET)
    public String getCharityProfile(@RequestParam String  reg, Model model) {


        List<Charity> charity = charityService.findByRegistrationNumber(reg);

        if (charity.size() == 1) {
            model.addAttribute("charity", charity.get(0));
        } else if (charity.size() > 1) {
            throw new NonUniqueResourceException("More than one charity has that registration id", "/findCharity");
        } else {
            throw new MissingResourceException("No such charity", "/findCharity");
        }


        return "CharityProfile";


    }



}


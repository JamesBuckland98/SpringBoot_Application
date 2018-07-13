package com.nsa.cm6213.example.charity2018.controllers;


import com.nsa.cm6213.example.charity2018.domain.Charity;
import com.nsa.cm6213.example.charity2018.services.CharityService;
import com.nsa.cm6213.example.charity2018.services.CharityServiceStatic;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@Controller
public class CharityProfile {


    @RequestMapping(path = "/charity/{reg}", method = RequestMethod.GET)
    public String getCharityProfile(@PathVariable String reg, Model model) {

        System.out.println(reg);

        CharityService charityService = CharityServiceStatic.getInstance();
        Optional<Charity> charity = charityService.findByRegistrationNumber(reg);

        model.addAttribute("charity", charity.get());

        return "CharityProfile";


    }

}


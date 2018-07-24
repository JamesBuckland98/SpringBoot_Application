package com.nsa.cm6213.example.charity2018.controllers.api;


import com.nsa.cm6213.example.charity2018.controllers.exceptions.MissingResourceException;
import com.nsa.cm6213.example.charity2018.domain.Charity;
import com.nsa.cm6213.example.charity2018.services.CharityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path="/api")
public class CharityAPI {

    private CharityService charityService;

    @Autowired
    public CharityAPI(CharityService aService) {
        charityService = aService;
    }

    @RequestMapping(path="/charity/{reg}", method=RequestMethod.GET)
    public ResponseEntity<Charity> getCharity(@PathVariable String reg, Model model){

        Optional<Charity> charity = charityService.findByRegistrationNumber(reg);

        if (charity.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(charity.get());

        } else {
            throw new MissingResourceException("No such charity");
        }
    }



}

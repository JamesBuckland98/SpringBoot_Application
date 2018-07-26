package com.nsa.cm6213.example.charity2018.controllers.api;


import com.nsa.cm6213.example.charity2018.controllers.exceptions.MissingResourceException;
import com.nsa.cm6213.example.charity2018.domain.Charity;
import com.nsa.cm6213.example.charity2018.services.CharityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/api")
public class CharityAPI {

    private CharityService charityService;

    @Autowired
    public CharityAPI(CharityService aService) {
        charityService = aService;
    }

    @RequestMapping(path = "/charity/{id}", method = RequestMethod.GET)
    public ResponseEntity<Charity> getCharity(@PathVariable Long id, Model model) {

        try {
            Charity charity = charityService.findById(id);
            return ResponseEntity.status(HttpStatus.OK).body(charity);
        } catch (MissingResourceException e) {
            throw e;
        }
    }



}

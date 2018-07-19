package com.nsa.cm6213.example.charity2018.controllers;

import com.nsa.cm6213.example.charity2018.services.CharityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@SessionAttributes("regId")
@Controller
public class DonationController {

    private CharityService charityService;

    @Autowired
    public DonationController(CharityService aService) {
        charityService = aService;
    }

    @RequestMapping(path = "/donate/{regId}", method = RequestMethod.GET)
    public String startDonation(@PathVariable String regId, Model model) {

        model.addAttribute("regId", regId);
        return "donation";

    }

    @RequestMapping(path = "/payment", method = RequestMethod.GET)
    public String takePaymentForDonation(@SessionAttribute String regId, Model model) {

        System.out.println("From session..." + regId);

        model.addAttribute("regId", regId);
        return "donationPayment";
    }


}

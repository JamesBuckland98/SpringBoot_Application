package com.nsa.cm6213.example.charity2018.controllers;

import com.nsa.cm6213.example.charity2018.services.CharityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@SessionAttributes({"regId", "donor"})
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

    @RequestMapping(path="donorDetails", method=RequestMethod.POST)
    public String donorDetails(DonorForm donor, @SessionAttribute String regId, Model model ){

        System.out.println(donor);

        System.out.println("From session..." + regId);
        model.addAttribute("donor",donor);
        model.addAttribute("regId", regId);
        return "donationPayment";
    }

    @RequestMapping(path="makePayment", method=RequestMethod.POST)
    public String donorDetails(PaymentForm payment, @SessionAttribute DonorForm donor, @SessionAttribute String regId, Model model ){

        System.out.println("From session..." + donor);

        System.out.println("From session..." + regId);
        System.out.println(payment);
        model.addAttribute("last4Card",payment.getCardNumber().substring(payment.getCardNumber().length()-4));
        model.addAttribute("donor",donor);
        model.addAttribute("regId", regId);
        return "paymentConfirmation";
    }




}

package com.nsa.cm6213.example.charity2018.controllers;

import com.nsa.cm6213.example.charity2018.services.CharityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@SessionAttributes({"id", "donor"})
@Controller
public class DonationController {

    private CharityService charityService;

    @Autowired
    public DonationController(CharityService aService) {
        charityService = aService;
    }

    @RequestMapping(path = "/donate/id", method = RequestMethod.GET)
    public String startDonation(@PathVariable Long id, Model model) {

        model.addAttribute("id", id);
        return "donation";

    }

    @RequestMapping(path="donorDetails", method=RequestMethod.POST)
    public String donorDetails(DonorForm donor, @SessionAttribute Long id, Model model ){

        System.out.println(donor);

        System.out.println("From session..." + id);
        model.addAttribute("donor",donor);
        model.addAttribute("regId", id);
        return "donationPayment";
    }

    @RequestMapping(path="makePayment", method=RequestMethod.POST)
    public String donorDetails(PaymentForm payment, @SessionAttribute DonorForm donor, @SessionAttribute Long id, Model model ){

        System.out.println("From session..." + donor);

        System.out.println("From session..." + id);
        System.out.println(payment);
        model.addAttribute("last4Card",payment.getCardNumber().substring(payment.getCardNumber().length()-4));
        model.addAttribute("donor",donor);
        model.addAttribute("regId", id);
        return "paymentConfirmation";
    }




}

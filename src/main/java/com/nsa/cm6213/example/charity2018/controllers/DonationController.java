package com.nsa.cm6213.example.charity2018.controllers;

import com.nsa.cm6213.example.charity2018.services.CharityService;
import com.nsa.cm6213.example.charity2018.services.DonationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@SessionAttributes({"id", "donor", "payment"})
@Controller
public class DonationController {

    static final Logger LOG = LoggerFactory.getLogger(DonationController.class);


    private CharityService charityService;
    private DonationService donationService;

    @Autowired
    public DonationController(CharityService aService, DonationService donService) {

        charityService = aService;
        donationService = donService;
    }

    @RequestMapping(path = "/donate/{id}", method = RequestMethod.GET)
    public String startDonation(@PathVariable Long id, Model model) {

        model.addAttribute("id", id);
        return "donation";

    }

    @RequestMapping(path = "donorDetails", method = RequestMethod.POST)
    public String donorDetails(DonorForm donor, @SessionAttribute Long id, Model model) {

        LOG.debug(donor.toString());

        LOG.debug("From session..." + id);
        model.addAttribute("donor", donor);
        model.addAttribute("id", id);
        return "donationPayment";
    }

    @RequestMapping(path = "paymentDetails", method = RequestMethod.POST)
    public String donorDetails(@SessionAttribute PaymentForm payment, @SessionAttribute DonorForm donor, @SessionAttribute Long id, Model model) {

        LOG.debug("From session..." + donor);
        LOG.debug("From session..." + id);
        LOG.debug(payment.toString());


        model.addAttribute("last4Card", payment.getCardNumber().substring(payment.getCardNumber().length() - 4));
        model.addAttribute("donor", donor);
        model.addAttribute("id", id);


        return "paymentConfirmation";
    }

    @RequestMapping(path = "/confirm", method = RequestMethod.GET)
    public String confirmDonation(@SessionAttribute DonorForm donor, @SessionAttribute Long id, @SessionAttribute PaymentForm payment, Model model) {
        donationService.createDonation(donor, payment, id);
        return "receipt";
    }


}

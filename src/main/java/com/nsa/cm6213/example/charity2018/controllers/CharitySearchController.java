package com.nsa.cm6213.example.charity2018.controllers;

import com.nsa.cm6213.example.charity2018.controllers.exceptions.MissingResourceException;
import com.nsa.cm6213.example.charity2018.controllers.exceptions.NonUniqueResourceException;
import com.nsa.cm6213.example.charity2018.domain.Charity;
import com.nsa.cm6213.example.charity2018.services.CharityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
//This is a controller.  It is NOT a RestController.  It is part of the Spring MVC framework so more aligned with server-side generation of HTML.
public class CharitySearchController {

    static final Logger LOG = LoggerFactory.getLogger(CharitySearchController.class);


    private CharityService charityService;

    @Autowired
    public CharitySearchController(CharityService aService) {
        charityService = aService;
    }


    @RequestMapping(path = "/findCharity", method = RequestMethod.GET)
    public String getCharityProfile(@RequestParam("search") String search, Model model) {

        LOG.debug("Handling /findCharity");

        List<Charity> charity = charityService.findCharities(search);


        model.addAttribute("searchTerm", search);
            model.addAttribute("matches", charity);


        return "charitySearchResults"; //the return is the name of the next page (but we're not doing templating, so we redirect to the home page.


    }

    /*

    We can use @Controllers to create dynamic server-side pages.  This is normally achieved
    using a templating framework such as Thymeleaf.  There are plenty of online resources on the combination of
    Spring (boot) and Thymeleaf, but be careful with versions.

    We are going to tend towards JQuery so we will inject HTML into a static page using data from REST api end-points.

    This is a step towards full single-page applications and reactive applications.

    Spring Boot 2.0 will have full support for reactive applications (because Spring 5.0) has it.



     */


}


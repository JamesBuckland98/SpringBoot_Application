package com.nsa.cm6213.example.charity2018.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
//This is a controller.  It is NOT a RestController.  It is part of the Spring MVC framework so more aligned with server-side generation of HTML.
public class FormController {
    @RequestMapping(path = "/findCharity", method = RequestMethod.POST) //handles a form POST to /name
    public String postName(@ModelAttribute CharityForm in_form, Model model) { //ModelAttribute maps the form to an object
        System.out.println("name equals " + in_form.getName());//we can access the form as a normal object
        return "redirect:/home.html"; //the return is the name of the next page (but we're not doing templating, so we redirect to the home page.
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


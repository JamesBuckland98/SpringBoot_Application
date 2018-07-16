package com.nsa.cm6213.example.charity2018.controllers;

import com.nsa.cm6213.example.charity2018.controllers.exceptions.MissingResourceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class NotFoundControllerAdvice {

    public static final String DEFAULT_MISSING_RESOURCE_VIEW = "404";


    @ResponseStatus(HttpStatus.NOT_FOUND)  // 404
    @ExceptionHandler(MissingResourceException.class)
    public ModelAndView handleMissingResource(HttpServletRequest req, Exception e) {


        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURL());
        mav.addObject("message", e.getMessage());
        mav.setViewName(DEFAULT_MISSING_RESOURCE_VIEW);
        return mav;

    }
}


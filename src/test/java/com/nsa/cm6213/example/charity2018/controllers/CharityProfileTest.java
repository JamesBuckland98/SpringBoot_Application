package com.nsa.cm6213.example.charity2018.controllers;


import com.nsa.cm6213.example.charity2018.controllers.exceptions.MissingResourceException;
import com.nsa.cm6213.example.charity2018.domain.Charity;
import com.nsa.cm6213.example.charity2018.services.CharityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CharityProfileTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CharityService charityService;



    /**
     * This is the main API for getting access to a specific charity.  The issue here is that it reveals the internal id.
     *
     * @throws Exception
     */
    @Test
    public void shouldReturnNSPCCById() throws Exception {

        Charity nspcc = new Charity(2L,"National Society for the Prevention of Cruelty to Children", "NSPCC", "Kids charity", "nspcc", "12345678", true);

        given(this.charityService.findById(2L)).willReturn(nspcc);


        this.mockMvc.perform(get("/charity/2")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("National Society")))
                .andExpect(model().attribute("charity",

                        allOf(
                                hasProperty("id", is(2L)),
                                hasProperty("acronym", is("NSPCC")),
                                hasProperty("logoPath", is("nspcc")))))
        ;
    }

    @Test
    public void shouldReturn404() throws Exception {

        Charity nspcc = new Charity(2L, "National Society for the Prevention of Cruelty to Children", "NSPCC", "Kids charity", "nspcc", "12345678", true);

        given(this.charityService.findById(2L)).willReturn(nspcc);
        given(this.charityService.findById(3L)).willThrow(new MissingResourceException("No such charity"));


        this.mockMvc.perform(get("/charity/3")).andDo(print()).andExpect(status().isNotFound())
                .andExpect(content().string(containsString("No such charity")))

        ;
    }







}


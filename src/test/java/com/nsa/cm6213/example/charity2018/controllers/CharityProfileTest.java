package com.nsa.cm6213.example.charity2018.controllers;


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

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CharityProfileTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CharityService charityService;

    @Test
    public void shouldReturnNSPCCByRegId() throws Exception {

        Charity nspcc = new Charity(2L,"National Society for the Prevention of Cruelty to Children", "NSPCC", "Kids charity", "nspcc", "12345678", true);
        //Charity rspca = new Charity(3L,"Royal Society for the Prevention of Cruelty to Animals", "RSPCA", "Animal charity", "rspca", "87654321", true);
        ArrayList<Charity> charities = new ArrayList<>();
        charities.add(nspcc);
        //charities.add(rspca);

        given(this.charityService.findByRegistrationNumber("12345678")).willReturn(charities);


        this.mockMvc.perform(get("/charity?reg=12345678")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("National Society")))
        ;
    }

    @Test
    public void shouldReturnNSPCCById() throws Exception {

        Charity nspcc = new Charity(2L,"National Society for the Prevention of Cruelty to Children", "NSPCC", "Kids charity", "nspcc", "12345678", true);
        //Charity rspca = new Charity(3L,"Royal Society for the Prevention of Cruelty to Animals", "RSPCA", "Animal charity", "rspca", "87654321", true);
        //ArrayList<Charity> charities = new ArrayList<>();
        //charities.add(nspcc);
        //charities.add(rspca);

        given(this.charityService.findById(2L)).willReturn(Optional.of(nspcc));


        this.mockMvc.perform(get("/charity/2")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("National Society")))
        ;
    }

    @Test
    public void shouldReturnTwoCatsAndFail() throws Exception {

        Charity catsCardiff = new Charity(2L,"Cats Protection Cardiff", "", "cats charity", "cpl", "12345678", true);
        Charity catsSwansea= new Charity(3L,"Cat Protection Swansea", "", "cats charity", "cpl", "12345678", true);
        ArrayList<Charity> charities = new ArrayList<>();
        charities.add(catsCardiff);
        charities.add(catsSwansea);

        given(this.charityService.findByRegistrationNumber("12345678")).willReturn(charities);


        this.mockMvc.perform(get("/charity?reg=12345678")).andDo(print()).andExpect(status().isConflict())
                .andExpect(content().string(containsString("More than one")))
        ;
    }


    @Test
    public void shouldReturnA404() throws Exception {

        given(this.charityService.findByRegistrationNumber("123456789")).willReturn(new ArrayList<Charity>());


        this.mockMvc.perform(get("/charity?reg=123456789")).andDo(print()).andExpect(status().isNotFound())
                .andExpect(content().string(containsString("No such charity")));
    }


}


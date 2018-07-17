package com.nsa.cm6213.example.charity2018.controllers;


import com.nsa.cm6213.example.charity2018.domain.Charity;
import com.nsa.cm6213.example.charity2018.services.CharityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @see "http://spring.io/guides/gs/testing-web/"
 */


@RunWith(SpringRunner.class)
@SpringBootTest()
@AutoConfigureMockMvc
public class CharitySearchTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CharityService charityService;


    @Test
    public void shouldReturnHomePage() throws Exception {


        this.mockMvc.perform(get("/home.html")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Welcome to Charity Giving")));
    }

    @Test
    public void shouldFindNSPCC() throws Exception {

        Charity nspcc = new Charity("NSPCC", "Kids charity", "nspcc", "12345678", true);
        Charity rspca = new Charity("RSPCA", "Animal charity", "rspca", "87654321", true);
        ArrayList<Charity> charities = new ArrayList<>();
        charities.add(nspcc);
        charities.add(rspca);

        given(this.charityService.findCharities("NSPCC")).willReturn(charities);

        mockMvc.perform(post("/findCharity")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name", "NSPCC")

        )
                .andExpect(status().isOk())
                .andExpect(view().name("charitySearchResults"))
                .andExpect(model().attribute("searchTerm", "NSPCC"))
                .andExpect(model().attribute("matches", hasItem(nspcc)))
                .andExpect(content().string(stringContainsInOrder(Arrays.asList("<div id=charityListContainer", "NSPCC", "12345678", "</div>"))));
                ;



    }

    /**
     * What should happen if the charity can't be found?
     * Page should say that no charities could be matched and offer a link back to home page.
     */

    @Test
    public void shouldNotFindCRUK() throws Exception {

        Charity nspcc = new Charity("NSPCC", "Kids charity", "nspcc", "12345678", true);
        Charity rspca = new Charity("RSPCA", "Animal charity", "rspca", "87654321", true);
        ArrayList<Charity> charities = new ArrayList<>();
        charities.add(nspcc);
        charities.add(rspca);

        given(this.charityService.findCharities("NSPCC")).willReturn(charities);

        mockMvc.perform(post("/findCharity")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name", "Cancer Research UK")

        )
                .andExpect(status().isOk())
                .andExpect(view().name("charitySearchResults"))
                .andExpect(model().attribute("searchTerm", "Cancer Research UK"))
                .andExpect(model().attribute("matches", iterableWithSize(0)))
                .andExpect(content().string(stringContainsInOrder(Arrays.asList("<div id=missingCharityMessage>","No matching charity","</div>"))));
        ;



    }


}



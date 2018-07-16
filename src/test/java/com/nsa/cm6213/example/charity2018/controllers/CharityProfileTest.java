package com.nsa.cm6213.example.charity2018.controllers;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
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

    @Test
    public void shouldReturnNSPCC() throws Exception {
        this.mockMvc.perform(get("/charity/12345678")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("NSPCC")));
    }

    @Test
    public void shouldReturnA404() throws Exception {
        this.mockMvc.perform(get("/charity/12345678999")).andDo(print()).andExpect(status().isNotFound())
                .andExpect(content().string(containsString("No such charity")));
    }


}


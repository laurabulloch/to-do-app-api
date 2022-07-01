package com.verint.todoappapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest
class ToDosControllerTest {

    @Autowired
    private MockMvc mockMvc;

    //method_scenario_expectation
    @Test
    void getToDos_noToDos_emptyArray() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/to-dos")).andExpect(content().json("[]"));


    }

}
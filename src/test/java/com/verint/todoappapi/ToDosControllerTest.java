package com.verint.todoappapi;

import com.verint.todoappapi.model.ToDoDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.*;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest
class ToDosControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ToDosService toDosService;

    //method_scenario_expectation
    @Test
    void getToDos_noToDos_emptyArray() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/to-dos")).andExpect(content().json(null));
    }
    @Test
    void getToDos_oneToDo_toDoValue() throws Exception {
        ToDoDTO testItem = new ToDoDTO();
        testItem.setName("Item 1");
        List<ToDoDTO> testData = null;
        testData.add(testItem);

        when(toDosService.getAll()).thenReturn(testData);

        mockMvc.perform(MockMvcRequestBuilders.get("/to-dos")).andExpect(content().json(testData.toString()));
    }

}
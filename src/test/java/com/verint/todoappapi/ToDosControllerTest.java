package com.verint.todoappapi;

import com.verint.todoappapi.model.ToDoDTO;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest
class ToDosControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ToDosService toDosService;

    //method_scenario_expectation
    @Test
    void getToDos_callsService() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/to-dos"));

        verify(toDosService).getAll();
    }
    @Test
    void getToDos_noToDos_emptyArray() throws Exception {
        when(toDosService.getAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders.get("/to-dos")).andExpect(content().json("[]"));
    }

    @Test
    void save_callsService() throws Exception {
        ArgumentCaptor<ToDoDTO> argumentCaptor = ArgumentCaptor.forClass(ToDoDTO.class);
        ToDoDTO test = ToDoBuilder.builder().name("test 1").build();
        String jsonString = ToDoBuilder.generateDTOJson(1L, "test 1");
        mockMvc.perform(MockMvcRequestBuilders.post("/to-dos").contentType(APPLICATION_JSON).content(jsonString));

        verify(toDosService).create(argumentCaptor.capture());
    }

    @Test
    void save_callsServiceWithDTOGiven() throws Exception {
        ArgumentCaptor<ToDoDTO> argumentCaptor = ArgumentCaptor.forClass(ToDoDTO.class);
        ToDoDTO test = ToDoBuilder.builder().name("test 1").build();
        String jsonString = ToDoBuilder.generateDTOJson(1L, "test 1");

        mockMvc.perform(MockMvcRequestBuilders.post("/to-dos").contentType(APPLICATION_JSON).content(jsonString));

        verify(toDosService).create(argumentCaptor.capture());
        ToDoDTO dto = argumentCaptor.getValue();
        assert(dto.getName().equals(test.getName()));
    }

    @Test
    void save_returnsDTO() throws Exception {
        ArgumentCaptor<ToDoDTO> argumentCaptor = ArgumentCaptor.forClass(ToDoDTO.class);
        String jsonString = ToDoBuilder.generateDTOJson(1L, "test 1");
        ToDoDTO receivedDTO = ToDoBuilder.builder().id(1L).name("test 1").build();
        String returnDTOToJson = ToDoBuilder.generateDTOJson(1L, "test 1");


        when(toDosService.create(argumentCaptor.capture())).thenReturn(receivedDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/to-dos").contentType(APPLICATION_JSON).content(jsonString)).andExpect(content().json(returnDTOToJson));
    }

}
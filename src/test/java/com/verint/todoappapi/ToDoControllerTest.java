package com.verint.todoappapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.verint.todoappapi.model.ToDoDTO;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class ToDoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ToDoService toDoService;

    @Test
    void get_callsService() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/to-dos"));

        verify(toDoService).getAll();
    }
    @Test
    void get_noToDos_emptyArray() throws Exception {
        when(toDoService.getAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders.get("/to-dos")).andExpect(content().json("[]"));
    }
    @Test
    void save_callsServiceWithDTO() throws Exception {
        ArgumentCaptor<ToDoDTO> argumentCaptor = ArgumentCaptor.forClass(ToDoDTO.class);
        ToDoDTO test = new ToDoDTO();
        test.setName("item 1");
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(test);
        mockMvc.perform(MockMvcRequestBuilders.post("/to-dos").contentType(APPLICATION_JSON).content(jsonString));

        verify(toDoService).create(argumentCaptor.capture());
        ToDoDTO dto = argumentCaptor.getValue();
        assert(dto.getName().equals(test.getName()));
    }
    @Test
    void save_returnsDTO() throws Exception {
        ArgumentCaptor<ToDoDTO> argumentCaptor = ArgumentCaptor.forClass(ToDoDTO.class);
        ToDoDTO testReceived = ToDoDTOBuilder.builder().id(1L).name("Item 1").build();

        when(toDoService.create(argumentCaptor.capture())).thenReturn(testReceived);

        mockMvc.perform(MockMvcRequestBuilders.post("/to-dos")
                .contentType(APPLICATION_JSON)
                .content("""
                        {"name": "Item 1"}
                         """)).andExpect(content()
                .json("""
                                {
                                    "id": 1,
                                    "name": "Item 1" 
                                }
                                """));
    }
    @Test
    void delete_shouldCallServiceWithDelete()throws Exception{
        when(toDoService.delete(any())).thenReturn(true);

        mockMvc.perform(delete("/to-dos/1"));
        verify(toDoService).delete(1L);
    }
    @Test
    void delete_shouldRespond204WhenSuccessful()throws Exception{
        when(toDoService.delete(any())).thenReturn(true);

        mockMvc.perform(delete("/to-dos/1")).andExpect(status().isNoContent());

    }
    @Test
    void delete_shouldRespond404WhenIdNotFound()throws Exception{
        when(toDoService.delete(any())).thenReturn(false);

        mockMvc.perform(delete("/to-dos/1")).andExpect(status().isNotFound());
    }

    @Test
    void edit_callServicePatchWithIdGiven() throws Exception{
        when(toDoService.edit(any() ,any())).thenReturn(true);

        mockMvc.perform(patch("/to-dos/1")
                .contentType(APPLICATION_JSON)
                .content("""
                         {"name": "Item Patch"}
                         """));

        verify(toDoService).edit(1L,ToDoDTOBuilder.builder().name("Item Patch").build());
    }

    @Test
    void edit_idInDatabase_returnsSuccessMessage() throws Exception{
        when(toDoService.edit(any(),any())).thenReturn(true);

        mockMvc.perform(patch("/to-dos/1")
                        .contentType(APPLICATION_JSON)
                        .content("""
                         {"name": "Item"}
                         """))
                .andExpect(
                        status().isNoContent());
    }

    @Test
    void edit_idNotInDatabase_returnsFailMessage() throws Exception{
        when(toDoService.edit(any(),any())).thenReturn(false);

        mockMvc.perform(patch("/to-dos/100")
                        .contentType(APPLICATION_JSON)
                        .content("""
                         {"name": "Item"}
                         """))
                .andExpect(
                        status().isNotFound());
    }

}

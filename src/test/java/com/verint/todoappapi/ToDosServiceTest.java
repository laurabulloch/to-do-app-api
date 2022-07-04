package com.verint.todoappapi;

import com.verint.todoappapi.model.ToDoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ToDosServiceTest{
    ToDosService toDosService;

    @BeforeEach
    void setUp() {toDosService = new ToDosService();}

    @Test
    void getAll_toDoEmpty_emptyList() {
        assertSame(Collections.emptyList(), toDosService.getAll());
    }
    @Test
    void setToDos_testDTO_listTestDTO() {
        ToDoDTO testDTO = new ToDoDTO();
        testDTO.setId(Long.getLong("1"));
        testDTO.setName("test_data1");
        List<ToDoDTO> testList = new ArrayList<>();
        testList.add(testDTO);

        toDosService.setToDos(testList);
        assertSame(toDosService.getToDos(), testList);
    }
    @Test
    void getAll_withToDo_arrayToDos() {
        ToDoDTO testDTO = new ToDoDTO();
        testDTO.setId(Long.getLong("1"));
        testDTO.setName("test_data1");
        List<ToDoDTO> testList = new ArrayList<>();
        testList.add(testDTO);

        toDosService.setToDos(testList);
        assertSame(testList, toDosService.getAll());
    }

}
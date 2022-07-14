package com.verint.todoappapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.verint.todoappapi.model.ToDoDTO;
import lombok.Builder;

public class ToDoBuilder {
    @Builder
    public static ToDoDTO generateToDo(Long id, String name) {
        ToDoDTO toDoDTO = new ToDoDTO();
        toDoDTO.setId(id);
        toDoDTO.setName(name);
        return toDoDTO;
    }

    public static String generateDTOJson (Long id, String name) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(generateToDo(id, name));
    }

    public static String generateDTOJson (String name) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(generateToDo(null, name));
    }
}

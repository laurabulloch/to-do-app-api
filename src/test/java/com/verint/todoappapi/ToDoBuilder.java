package com.verint.todoappapi;

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
}

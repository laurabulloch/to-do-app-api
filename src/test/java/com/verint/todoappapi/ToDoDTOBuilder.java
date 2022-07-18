package com.verint.todoappapi;

import com.verint.todoappapi.model.ToDoDTO;
import lombok.Builder;

public class ToDoDTOBuilder {
    @Builder (builderClassName = "ToDoDTOImpl")
    public static ToDoDTO generateToDo(Long id, String name){
        ToDoDTO dto = new ToDoDTO();
        dto.setName(name);
        dto.setId(id);
        return dto;
    }
}

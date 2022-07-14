package com.verint.todoappapi;

import com.verint.todoappapi.model.ToDoDTO;
import org.mapstruct.Mapper;

@Mapper
public interface ToDoMapper {
        ToDoDTO entityToDTO(ToDo toDo);
        ToDo dtoToEntity(ToDoDTO toDoDTO);

}

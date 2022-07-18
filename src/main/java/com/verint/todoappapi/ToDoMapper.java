package com.verint.todoappapi;

import com.verint.todoappapi.model.ToDoDTO;
import lombok.Generated;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Generated
@Mapper (componentModel = SPRING )
public interface ToDoMapper {
        ToDoDTO entityToDTO(ToDo toDo);
        ToDo dtoToEntity(ToDoDTO toDoDTO);
}

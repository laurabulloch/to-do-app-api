package com.verint.todoappapi;

import com.verint.todoappapi.model.ToDoDTO;
import com.verint.todoappapi.model.ToDosPostRequestBodyDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ToDosService {

    private final ToDoRepository toDoRepository;

    public List<ToDoDTO> getAll(){
       return toDoRepository.findAll().stream().map(toDo -> {
            ToDoDTO toDoDTO = new ToDoDTO();
            toDoDTO.setId(toDo.getId());
            toDoDTO.setName(toDo.getName());
            return toDoDTO;
        }).toList();
    }

    public ToDoDTO create(ToDoDTO body) {
        return null;
    }
}

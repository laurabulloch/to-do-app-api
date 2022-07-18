package com.verint.todoappapi;

import com.verint.todoappapi.model.ToDoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ToDoService {

    private final ToDoRepository toDoRepository;
    private final ToDoMapper toDoMapper;

    public List<ToDoDTO> getAll(){
       return toDoRepository.findAll().stream().map(toDo -> {
            ToDoDTO toDoDTO = new ToDoDTO();
            toDoDTO.setId(toDo.getId());
            toDoDTO.setName(toDo.getName());
            return toDoDTO;
        }).toList();
    }

    public ToDoDTO create(ToDoDTO body) {
        return toDoMapper.entityToDTO(toDoRepository.save(toDoMapper.dtoToEntity(body)));
    }
}

package com.verint.todoappapi;

import com.verint.todoappapi.model.ToDoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public boolean delete(Long id) {
        try {
            toDoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException emptyResultDataAccessException){
            return false;
        }
        return true;
    }

    public boolean edit(Long id, ToDoDTO editedToDo) {
        Optional<ToDo> toDoOptional = toDoRepository.findById(id);
        if (toDoOptional.isPresent()){
            ToDo toDo = toDoOptional.get();
            toDo.setName(editedToDo.getName());
            toDoRepository.save(toDo);
            return true;
        } else {
            return false;
        }
    }
}

package com.verint.todoappapi;

import com.verint.todoappapi.model.ToDoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
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

    public boolean delete(Long id) {
        try {
            toDoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException emptyResultDataAccessException){
            return false;
        }
        return true;
    }

    public boolean edit(Long id, ToDoDTO editedToDo) {
        try {
            toDoRepository.findById(id);
        } catch (EmptyResultDataAccessException emptyResultDataAccessException){
            return false;
        }
        ToDoDTO updatedToDoDTO = new ToDoDTO();
        updatedToDoDTO.setId(id);
        updatedToDoDTO.setName(editedToDo.getName());
        toDoMapper.entityToDTO(toDoRepository.save(toDoMapper.dtoToEntity(updatedToDoDTO)));

        return true;

    }
}

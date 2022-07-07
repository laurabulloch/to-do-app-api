package com.verint.todoappapi;

import com.verint.todoappapi.model.ToDoDTO;
import org.springframework.stereotype.Service;
import java.util.List;

import static java.util.Collections.singletonList;

@Service
public class ToDosService {

    public List<ToDoDTO> getAll(){
        ToDoDTO toDo = new ToDoDTO();
        toDo.setId(1L);
        toDo.setName("Item test");

        return singletonList(toDo);
    }
}

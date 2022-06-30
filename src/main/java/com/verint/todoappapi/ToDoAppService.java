package com.verint.todoappapi;
;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ToDoAppService {
    private final ToDoRepository toDoRepository;

    @Autowired
    public ToDoAppService(ToDoRepository toDoRepository){
        this.toDoRepository = toDoRepository;
    }

    public List getAll(){
        return toDoRepository.findAll().stream().toList();
    }
}

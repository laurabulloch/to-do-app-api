package com.verint.todoappapi;

import com.verint.todoappapi.model.ToDoDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ToDosService {

    private List<ToDoDTO> toDos;

    public ToDosService() {
        this.toDos = new ArrayList<>();
    }

    public List<ToDoDTO> getAll(){
        return getToDos();
    }

    public void setToDos(List<ToDoDTO> toDos){this.toDos = toDos;}

    public List<ToDoDTO> getToDos() {return toDos;}
}

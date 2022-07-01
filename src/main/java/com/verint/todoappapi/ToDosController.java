package com.verint.todoappapi;

import com.verint.todoappapi.api.TodosApi;
import com.verint.todoappapi.model.ToDoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ToDosController implements TodosApi {

    private ToDosService toDosService;

    public ToDosController(ToDosService toDosService){
        this.toDosService = toDosService;
    }

    @Override
    public ResponseEntity<List<ToDoDTO>> getToDos() {
        return ResponseEntity.ok(toDosService.getAll()) ;
    }
}

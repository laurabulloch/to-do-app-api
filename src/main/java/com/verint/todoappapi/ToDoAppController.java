package com.verint.todoappapi;

import com.verint.todoappapi.api.TodosApi;
import com.verint.todoappapi.model.ToDoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
public class ToDoAppController implements TodosApi {

    private final ToDoAppService toDoAppService;

    public ToDoAppController(ToDoAppService toDoAppService){
        this.toDoAppService = toDoAppService;
    }

    @Override
    public ResponseEntity<List<ToDoDTO>> getToDos() {return ok(toDoAppService.getAll());}
}

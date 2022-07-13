package com.verint.todoappapi;

import com.verint.todoappapi.api.TodosApi;
import com.verint.todoappapi.model.ToDoDTO;
import com.verint.todoappapi.model.ToDosPostRequestBodyDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class ToDosController implements TodosApi {

    private final ToDosService toDosService;

    @Override
    public ResponseEntity<List<ToDoDTO>> getToDos() {
        return ResponseEntity.ok(toDosService.getAll());
    }

    @Override
    public ResponseEntity<ToDoDTO> save(ToDoDTO body) {
        return ResponseEntity.ok(toDosService.create(body));
    }
}

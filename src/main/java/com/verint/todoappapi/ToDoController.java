package com.verint.todoappapi;

import com.verint.todoappapi.api.TodosApi;
import com.verint.todoappapi.model.ToDoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class ToDoController implements TodosApi {

    private final ToDoService toDoService;

    @Override
    public ResponseEntity<List<ToDoDTO>> getToDos() {
        return ResponseEntity.ok(toDoService.getAll());
    }

    @Override
    public ResponseEntity<ToDoDTO> save(ToDoDTO body) {
        return ResponseEntity.ok(toDoService.create(body));
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        Boolean success = toDoService.delete(id);

        if(success){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

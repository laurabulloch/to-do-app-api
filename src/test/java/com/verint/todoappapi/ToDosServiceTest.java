package com.verint.todoappapi;

import com.verint.todoappapi.model.ToDoDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.verint.todoappapi.ToDoMatcher.toDo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ToDosServiceTest{

    @Mock
    private ToDoRepository toDoRepository;

    @Test
    void getAll_shouldReturnSingleToDo() {
        ToDosService toDosService = new ToDosService(toDoRepository);
        ToDo todo = new ToDo();
        todo.setId(1L);
        todo.setName("Item 1");
        when(toDoRepository.findAll()).thenReturn(List.of(todo));
        List<ToDoDTO> toDoDTOList = toDosService.getAll();

        assertThat(toDoDTOList, contains(toDo(1L, "Item 1")));
    }

}
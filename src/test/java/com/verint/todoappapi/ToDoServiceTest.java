package com.verint.todoappapi;

import com.verint.todoappapi.model.ToDoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.mapstruct.factory.Mappers.getMapper;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ToDoServiceTest {

    @Mock
    private ToDoRepository toDoRepository;
    private ToDoService toDoService;

    @BeforeEach
    void setUp() {
        toDoService = new ToDoService(toDoRepository, getMapper(ToDoMapper.class));
    }

    @Test
    void getAll_shouldReturnSingleToDo() {
        ToDo todo = new ToDo();
        todo.setId(1L);
        todo.setName("Item 1");
        when(toDoRepository.findAll()).thenReturn(List.of(todo));
        List<ToDoDTO> toDoDTOList = toDoService.getAll();

        assertThat(toDoDTOList, contains(ToDoDTOMatcher.toDoDTO(1L, "Item 1")));
    }
    @Test
    void create_calledWithNull_shouldSaveToDo() {
        ArgumentCaptor<ToDo> toDoCaptor = ArgumentCaptor.forClass(ToDo.class);

        when(toDoRepository.save(any())).thenReturn(new ToDo(null,null));

        toDoService.create(ToDoDTOBuilder.builder()
                .name(null)
                .build());

        verify(toDoRepository).save(toDoCaptor.capture());

        assertThat(toDoCaptor.getValue(), is(ToDoMatcher.toDo(null, null)));
    }
    @Test
    void create_calledWithToDo_shouldReturnCreatedToDoDTO() {
        when(toDoRepository.save(any())).thenReturn(new ToDo(5L, "item 5"));

        ToDoDTO createdToDo = toDoService.create(ToDoDTOBuilder.builder()
                .name("item 5")
                .build());

        assertThat(createdToDo, is(ToDoDTOMatcher.toDoDTO(5L, "item 5")));
    }
    @Test
    void create_withNullToDo_shouldReturnNull() {
        when(toDoRepository.save(any())).thenReturn(null);

        ToDoDTO createdToDo = toDoService.create(null);
        assertThat(createdToDo, is(nullValue()));
    }

    @Test
    void delete_toDoExists_returnsTrue() {
        assertThat(toDoService.delete(1L), is(true));
    }

    @Test
    void delete_toDoesntExists_returnsTrue() {
        doThrow(new EmptyResultDataAccessException(1)).when(toDoRepository).deleteById(1L);

        assertThat(toDoService.delete(1L), is(false));
    }
    @Test
    void edit_toDoExists_shouldReturnTrue(){
        ToDo toDo = new ToDo(1L,"Item Patch");
        when(toDoRepository.findById(any()))
                .thenReturn(Optional.of(toDo));

        assertThat(toDoService.edit(1L, ToDoDTOBuilder.builder().name("Patched Name").build()), is(true));
    }
}
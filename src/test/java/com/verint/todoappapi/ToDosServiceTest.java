package com.verint.todoappapi;

import com.verint.todoappapi.model.ToDoDTO;
import lombok.RequiredArgsConstructor;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Objects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

class ToDosServiceTest{
    @Test
    void getAll_shouldReturnSingleToDo() {
        ToDosService toDosService = new ToDosService();
        List<ToDoDTO> toDoDTOList = toDosService.getAll();


        assertThat(toDoDTOList, contains());
    }
    @RequiredArgsConstructor
    static class ToDoMatcher extends TypeSafeMatcher<ToDoDTO>{

        private final long id;
        private final String name;

        @Override
        protected boolean matchesSafely(ToDoDTO item) {
            return Objects.equals(id, item.getId()) && Objects.equals(name, item.getName()) ;
        }

        @Override
        public void describeTo(Description description) {
            description.appendText("values didn't match");

        }
    }

}
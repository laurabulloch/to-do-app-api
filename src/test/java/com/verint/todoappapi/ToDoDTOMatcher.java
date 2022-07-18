package com.verint.todoappapi;

import com.verint.todoappapi.model.ToDoDTO;
import lombok.RequiredArgsConstructor;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.util.Objects;

@RequiredArgsConstructor
class ToDoDTOMatcher extends TypeSafeMatcher<ToDoDTO> {

    private final Long id;
    private final String name;

    static ToDoDTOMatcher toDoDTO(Long id, String name) {
        return new ToDoDTOMatcher(id, name);
    }

    static ToDoDTOMatcher toDoDTO(String name) {
        return new ToDoDTOMatcher(null, name);
    }

    @Override
    protected boolean matchesSafely(ToDoDTO item) {
        return Objects.equals(id, item.getId()) &&
                Objects.equals(name, item.getName());
    }

    @Override
    public void describeTo(Description description) {
        describe(description, id, name);
    }

    private void describe(Description description, Long id, String name) {
        description.appendText("<ToDoDTO(id:").appendValue(id).appendText(", name:").appendValue(name).appendText(")>");
    }

    @Override
    public void describeMismatchSafely(ToDoDTO item, Description mismatchDescription) {
        describe(mismatchDescription, item.getId(), item.getName());
    }
}

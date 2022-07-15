package com.verint.todoappapi;

import lombok.RequiredArgsConstructor;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.util.Objects;

@RequiredArgsConstructor
public class ToDoMatcher extends TypeSafeMatcher<ToDo> {
    private final Long id;
    private final String name;

    public static ToDoMatcher toDo(Long id, String name) {
        return new ToDoMatcher(id,name);
    }

    @Override
    protected boolean matchesSafely (ToDo item) {
        return Objects.equals(id, item.getId()) &&
                Objects.equals(name, item.getName());
    }

    @Override
    public void describeTo(Description description) {
        describe(description, id, name);
    }

    @Override
    protected void describeMismatchSafely(ToDo item, Description mismatchDescription) {
        describe(mismatchDescription, item.getId(), item.getName());;
    }

    private void describe(Description description, Long id, String name) {
        description.appendText("<ToDo(id=").appendValue(id).appendText(", name=").appendValue(name).appendText(")>");
    }
}

package com.verint.todoappapi;

import lombok.RequiredArgsConstructor;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

@RequiredArgsConstructor
public class ToDoMatcher extends TypeSafeMatcher<ToDo> {
    private final String name;

    public static ToDoMatcher toDo(String name) {
        return new ToDoMatcher(name);
    }


    @Override
    protected boolean matchesSafely (ToDo item) {
        return item.getName().equals(name);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(" ");
        describeTask(description, name);
    }

    @Override
    protected void describeMismatchSafely(ToDo item, Description mismatchDescription) {
        mismatchDescription.appendText("was ");
        describeTask(mismatchDescription, item.getName());
    }

    private void describeTask(Description description, String name) {
        description.appendText("<TaskDTO(name= ")
                .appendValue(name)
                .appendText(")>");
    }
}

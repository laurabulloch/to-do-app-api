package com.verint.todoappapi;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ToDo {

    private final Long id;
    private final String name;

}

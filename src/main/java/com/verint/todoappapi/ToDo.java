package com.verint.todoappapi;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class ToDo {

    @Id
    private Long id;
    private String name;

}

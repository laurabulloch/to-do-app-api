package com.verint.todoappapi;

import lombok.Generated;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages="com.verint.todoappapi")
@ComponentScan(basePackages={"com.verint"})
@Generated
@SpringBootApplication
public class ToDoAppApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToDoAppApiApplication.class, args);
    }

}

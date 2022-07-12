package com.verint.todoappapi;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration(proxyBeanMethods = false)
@EnableJpaRepositories("com.verint.todoappapi")
public class ToDoConfig {

}

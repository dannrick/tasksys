package com.example.tasksys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.example.tasksys.model")
@EnableJpaRepositories(basePackages = "com.example.tasksys.repository")
public class TasksysApplication {
    public static void main(String[] args) {
        SpringApplication.run(TasksysApplication.class, args);
    }
}

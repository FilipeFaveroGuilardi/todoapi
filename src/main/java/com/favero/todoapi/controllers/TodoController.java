package com.favero.todoapi.controllers;

import com.favero.todoapi.dto.TodoDTO;
import com.favero.todoapi.models.Todo;
import com.favero.todoapi.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {
    TodoService todoService;

    @Autowired
    public TodoController(TodoService todoController) {
        this.todoService = todoController;
    }


    @GetMapping
    public List<Todo> getAllTodos() {
        return todoService.getTodos();
    }

    @PostMapping
    public ResponseEntity<String> createATodo(@RequestBody TodoDTO todo) {
        try {
            todoService.createTodo(todo);

            return ResponseEntity.status(201).build();
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PutMapping("/{todoId}")
    public ResponseEntity<String> updateATodoNameOrDescription(@PathVariable() long todoId, TodoDTO todo) {
        try {
            todoService.updateTodo(todoId, todo);

            return ResponseEntity.status(200).build();
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<String> deleteATodo(@PathVariable() long todoId) {
        try {
            todoService.removeTodo(todoId);

            return ResponseEntity.status(200).build();
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PatchMapping("/{todoId}")
    public ResponseEntity<String> toggleTodoIsCompleted(@PathVariable(name = "todoId") long todoId) {
        try {
            todoService.toggleIsCompleted(todoId);

            return ResponseEntity.status(200).build();
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}

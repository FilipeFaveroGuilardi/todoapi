package com.favero.todoapi.services;

import com.favero.todoapi.dto.TodoDTO;
import com.favero.todoapi.models.Todo;
import com.favero.todoapi.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TodoService {
    TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> getTodos() {
        return todoRepository.findAll();
    }

    public void createTodo(TodoDTO todo) {
        if (todo.title().isEmpty() || todo.description().isEmpty()) {
            throw new IllegalArgumentException("Title or description are empty");
        }

        Todo newTodo = new Todo(todo.title(), todo.description(), false, LocalDateTime.now(), LocalDateTime.now());

        todoRepository.save(newTodo);
    }
}

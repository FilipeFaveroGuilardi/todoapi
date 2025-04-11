package com.favero.todoapi.services;

import com.favero.todoapi.dto.TodoDTO;
import com.favero.todoapi.models.Todo;
import com.favero.todoapi.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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


        Todo newTodo = new Todo(todo.title(), todo.description(), false, LocalDateTime.now(), LocalDateTime.now());

        todoRepository.save(newTodo);
    }

    public void updateTodo(long id, TodoDTO todo) {
        Todo oldTodo = findTodoById(id).orElseThrow();


        LocalDateTime oldTodoCreatedAtField = oldTodo.getCreatedAt();
        boolean oldTodoIsCompletedField = oldTodo.getIsCompleted();

        Todo updatedTodo = new Todo(todo.title(), todo.description(), oldTodoIsCompletedField, oldTodoCreatedAtField, LocalDateTime.now());
        updatedTodo.setId(id);

        todoRepository.save(updatedTodo);
    }

    public void removeTodo(Long id) {
        todoRepository.deleteById(id);
    }

    public void toggleIsCompleted(long id) {
        Todo todo = findTodoById(id).orElseThrow();

        todo.setIsCompleted(!todo.getIsCompleted());

        todoRepository.save(todo);
    }

    private Optional<Todo> findTodoById(long id) {
        return todoRepository.findById(id);
    }
}

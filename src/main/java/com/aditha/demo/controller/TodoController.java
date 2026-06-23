package com.aditha.demo.controller;

import com.aditha.demo.model.Todo;
import com.aditha.demo.service.TodoService;
import com.aditha.demo.dto.TodoRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import java.util.List;


@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public List<Todo> getTodos() {
        return todoService.getAllTodos();
    }

    @GetMapping("/{id}")
    public  ResponseEntity<Object> getTodoById(@PathVariable int id) {
        Todo todo = todoService.getTodoById(id);
        if(todo == null) {
            return ResponseEntity.status(404).body("Todo with id " + id + " not found");
        }
        return ResponseEntity.ok(todo);
    }

    @PostMapping
    public ResponseEntity<Todo> addTodo(@RequestBody TodoRequest request) {
        String title = request.getTitle();
        boolean completed = request.isCompleted();
        Todo savedTodo = todoService.addTodo(title, completed);

        return ResponseEntity.status(201).body(savedTodo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTodo(@PathVariable int id, @RequestBody TodoRequest request) {
        String title = request.getTitle();
        boolean completed = request.isCompleted();

        Todo updatedTodo = todoService.updateTodo(id, title,completed);
        if (updatedTodo == null) {
            return ResponseEntity.status(404).body("Todo with id " + id + " not found");
        }
        return ResponseEntity.ok(updatedTodo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTodo(@PathVariable int id) {
        String result = todoService.deleteTodo(id);
        if (result == null) {
            return ResponseEntity.status(404).body("Todo with id " + id + " not found");
        }
        return ResponseEntity.ok(result);
    }
}
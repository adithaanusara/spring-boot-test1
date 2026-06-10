package com.aditha.demo.controller;

import com.aditha.demo.model.Todo;
import com.aditha.demo.service.TodoService;

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
import java.util.Map;

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
    public Object getTodoById(@PathVariable int id) {
        Todo todo = todoService.getTodoById(id);
        if(todo == null) {
            return ResponseEntity.status(404).body("Todo with id " + id + " not found");
        }
        return ResponseEntity.ok(todo);
    }

    @PostMapping
    public Todo addTodo(@RequestBody Map<String, Object> body) {
        String title = body.get("title").toString();
        boolean completed = (boolean) body.get("completed");

        return todoService.addTodo(title, completed);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTodo(@PathVariable int id, @RequestBody Map<String, Object> body) {
        String title = body.get("title").toString();
        boolean completed = (boolean) body.get("completed");

        Todo updatedTodo = todoService.updateTodo(id, title,completed);
        if (updatedTodo == null) {
            return ResponseEntity.status(404).body("Todo with id " + id + " not found");
        }
        return ResponseEntity.ok(updatedTodo);
    }

    @DeleteMapping("/{id}")
    public String deleteTodo(@PathVariable int id) {
        return todoService.deleteTodo(id);
    }
}
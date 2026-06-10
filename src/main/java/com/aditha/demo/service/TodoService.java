package com.aditha.demo.service;

import com.aditha.demo.model.Todo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {

    private List<Todo> todos = new ArrayList<>();
    private int nextId = 1;

    public List<Todo> getAllTodos() {
        return todos;
    }

    public Todo getTodoById(int id) {
        for (Todo todo : todos) {
            if (todo.getId() == id) {
                return todo;
            }
        }

        return null;
    }

    public Todo addTodo(String title, boolean completed) {
        Todo todo = new Todo();

        todo.setId(nextId);
        todo.setTitle(title);
        todo.setCompleted(completed);

        nextId++;
        todos.add(todo);

        return todo;
    }

    public Todo updateTodo(int id, String title, boolean completed) {
        for (Todo todo : todos) {
            if (todo.getId() == id) {
                todo.setTitle(title);
                todo.setCompleted(completed);
                return todo;
            }
        }

        return null;
    }

    public String deleteTodo(int id) {
        for (Todo todo : todos) {
            if (todo.getId() == id) {
                todos.remove(todo);
                return "Todo with id " + id + " deleted successfully";
            }
        }

        return "Todo with id " + id + " not found";
    }
}
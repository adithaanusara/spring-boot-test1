package com.aditha.demo.controller;


import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.aditha.demo.model.Todo;
import java.util.List;
import java.util.ArrayList;


//me class eka Controller API ekak kiyala springboot ekata kiayanwa
@RestController
//මේකෙන් කියන්නේ මේ controller එකේ API URL එක පටන් ගන්නේ:
@RequestMapping("/api/todos")
public class TodoController {

    private List<Todo> todos = new ArrayList<>();
    private int nextId = 1;
    //getmapping request ekak awoth me method eka run krnna.
    @GetMapping
    public List<Todo> getTodos(){
        return todos;
    }

    @PostMapping
    public Todo addTodo(@RequestBody Todo todo){
        todo.setId(nextId);
        nextId++;
        todos.add(todo);
        return todo;
    }

    }
    
    


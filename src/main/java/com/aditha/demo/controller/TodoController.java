package com.aditha.demo.controller;


import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import com.aditha.demo.model.Todo;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;




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

    @GetMapping("/{id}")
       public Todo getTodoById(@PathVariable int id) {

        for(Todo todo : todos){
            if (todo.getId() == id) {
                return todo;
            }
        }
        return null;
       }

    @DeleteMapping("/{id}")
       public String deleteTodo(@PathVariable int id) {
        for (Todo todo : todos) {
            if (todo.getId() == id) {
                todos.remove(todo);
                return "Todo with id " + id + " delete successfully";
            }
        }
        return "Todo with id " + id + "not found";
       }

    @PutMapping("/{id}")
    public Todo updateTodo(@PathVariable int id, @RequestBody Map<String, Object> body) {
        for (Todo todo : todos) {
            if(todo.getId() == id) {
                String title = body.get("title").toString();
                Boolean completed = (Boolean) body.get("completed");
                todo.setTitle(title);
                todo.setCompleted(completed);
                return todo;
            }


        }
        
        return null;
    }

@PostMapping
public Todo addTodo(@RequestBody Map<String, Object> body) {

    String title = body.get("title").toString();
    boolean completed = (boolean) body.get("completed");

    Todo todo = new Todo();
    todo.setId(nextId);
    todo.setTitle(title);
    todo.setCompleted(completed);

    nextId++;
    todos.add(todo);

    return todo;
}

    }
    
    


package com.example.todoappl.controller;

import com.example.todoappl.model.ToDo;
import com.example.todoappl.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.todoappl.model.ToDo;
import com.example.todoappl.service.ToDoService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class ToDoController {

    private ToDoService service;

    @Autowired
    public void ToDoRestController(ToDoService service) {
        this.service = service;
    }

    public ToDoController(ToDoService service) {
        this.service = service;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<ToDo>> getAllToDoItems() {
        List<ToDo> todos = service.getAllToDoItems();
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ToDo> getToDoItemById(@PathVariable Long id) {
        ToDo todo = service.getToDoItemById(id);
        return new ResponseEntity<>(todo, HttpStatus.OK);
    }

    @PostMapping("/new")

    public ResponseEntity<Void> saveToDoItem(@RequestBody ToDo todo) {
        service.saveOrUpdateToDoItem(todo);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(todo.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateToDoItem(@PathVariable Long id, @RequestBody ToDo todo) {
        todo.setId(id);
        service.saveOrUpdateToDoItem(todo);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteToDoItem(@PathVariable Long id) {
        service.deleteToDoItem(id);
        return ResponseEntity.noContent().build();
    }
}

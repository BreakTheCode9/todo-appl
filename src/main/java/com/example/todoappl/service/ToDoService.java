package com.example.todoappl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todoappl.model.ToDo;
import com.example.todoappl.repo.IToDoRepo;

import java.util.List;
import java.util.Optional;

@Service
public class ToDoService {

    private final IToDoRepo repo;

    @Autowired
    public ToDoService(IToDoRepo repo) {
        this.repo = repo;
    }

    public List<ToDo> getAllToDoItems() {
        return repo.findAll();
    }

    public ToDo getToDoItemById(Long id) {
        Optional<ToDo> todo = repo.findById(id);
        return todo.orElse(null);
    }

    public boolean updateStatus(Long id) {
        ToDo todo = getToDoItemById(id);
        if (todo != null) {
            todo.setStatus("Completed");
            repo.save(todo);
            return true;
        }
        return false;
    }

    public boolean saveOrUpdateToDoItem(ToDo todo) {
        ToDo savedTodo = repo.save(todo);
        return savedTodo.getId() != null;
    }

    public boolean deleteToDoItem(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }
}

package com.example.todoappl.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.todoappl.model.ToDo;

@Repository
public interface IToDoRepo extends JpaRepository<ToDo, Long> {

}

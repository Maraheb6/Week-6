package com.example.spring_security.Repository;

import com.example.spring_security.Model.MyUser;
import com.example.spring_security.Model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo,Integer> {

    List<Todo> findAllByMyUserId(Integer userId);
    Todo findTodoById(Integer Id);

    List<Todo> findAllByMyUser(MyUser myUser);


}
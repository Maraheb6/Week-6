package com.example.spring_security.Controller;

import com.example.spring_security.Model.MyUser;
import com.example.spring_security.Model.Todo;
import com.example.spring_security.Service.TodoService;
import com.example.spring_security.dto.ApiResponse;
import com.example.spring_security.dto.Response;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/todo")
public class TodoController {

    private TodoService todoService;


    @GetMapping("/all-todos")
    public ResponseEntity getAllTodos(){
        return ResponseEntity.status(200).body(todoService.getAllTodo());
    }

    // User
    @GetMapping("/my-todos")
    public ResponseEntity getMyTodos(@AuthenticationPrincipal MyUser myUser){
        return ResponseEntity.status(200).body(todoService.getMyTodos(myUser.getId()));
    }

    // User
    @GetMapping("/{id}")
    public ResponseEntity getTodoById(@PathVariable Integer id , @AuthenticationPrincipal MyUser myUser){
        return ResponseEntity.status(200).body(todoService.getTodoById(id , myUser.getId()));
    }

    // User
    @PostMapping("/add-todo")
    public ResponseEntity addTodo(@RequestBody @Valid Todo todo, @AuthenticationPrincipal MyUser myUser){
        todoService.addTodo(todo,myUser.getId());
        return ResponseEntity.status(201).body(new ApiResponse("Todo Added"));
    }

    // User
    @PutMapping("/update/{id}")
    public ResponseEntity updateTodo(@RequestBody @Valid Todo todo, @PathVariable Integer id, @AuthenticationPrincipal MyUser myUser){
        todoService.updateTodo(id,todo,myUser.getId());
        return ResponseEntity.status(200).body(new ApiResponse("Todo Updated"));
    }

    // User
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteTodo(@PathVariable Integer id, @AuthenticationPrincipal MyUser myUser){
        todoService.deleteTodo(id,myUser.getId());
        return ResponseEntity.status(200).body(new ApiResponse("Todo deleted"));
    }

}
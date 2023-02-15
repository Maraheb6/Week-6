package com.example.hw28.Controller;

import com.example.hw28.ApiException.ApiException;
import com.example.hw28.Model.MyUser;
import com.example.hw28.Response.ApiResponse;
import com.example.hw28.Service.MyUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class MyUserController {
    private final MyUserService myUserService;
    //Get all Users
    @GetMapping("/get")
    public ResponseEntity getUser(){
        List<MyUser> myUsers=myUserService.getUser();
        return ResponseEntity.status(200).body(myUsers);
    }
    //Add new User...register
    @PostMapping("/register")
    public ResponseEntity addUser(@Valid @RequestBody MyUser myUser ){
        myUserService.register(myUser);
        return ResponseEntity.status(200).body(new ApiResponse("User Created"));
    }
    //Update User
    @PutMapping("/update")
    public ResponseEntity updateUser(@AuthenticationPrincipal @Valid@RequestBody MyUser newUser){
         myUserService.updateUser(newUser);
            return ResponseEntity.status(200).body(new ApiResponse("User Updated"));
    }
    //Delete User..Admin
    @DeleteMapping("/delete/{ID}")
    public  ResponseEntity deleteUser(@PathVariable Integer ID){
         myUserService.deleteUser(ID);
            return ResponseEntity.status(200).body(new ApiResponse("User Deleted"));
    }

    //get all customer order
    @GetMapping("/get-all-order/{id}")
    public ResponseEntity getAllOrders(@PathVariable Integer id){
        return ResponseEntity.status(200).body(myUserService.getAllOrders(id));
    }

    //get Customer by id
    @GetMapping("get-customer/{id}")
    private  ResponseEntity getCustomerById(@PathVariable Integer id){
        return ResponseEntity.status(200).body(myUserService.getCustomerById(id));
    }

    //login
    @PostMapping("/login")
    public ResponseEntity login(){
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Welcome back"));
    }

    //user
    @GetMapping("/my-orders")
    public ResponseEntity getMyOrders(@AuthenticationPrincipal MyUser myUser){
        return ResponseEntity.status(200).body(myUserService.getMyOrders(myUser.getId()));
    }

}

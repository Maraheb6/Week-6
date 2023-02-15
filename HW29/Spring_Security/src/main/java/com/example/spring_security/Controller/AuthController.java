package com.example.spring_security.Controller;

import com.example.spring_security.Model.MyUser;
import com.example.spring_security.Service.AuthService;
import com.example.spring_security.dto.ApiResponse;
import com.example.spring_security.dto.Response;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {


    private final AuthService authService;


//    @PostMapping("/register")
//    public ResponseEntity<Response> register(@RequestBody MyUser myUser){
//        authService.register(myUser);
//        return ResponseEntity.status(HttpStatus.CREATED).body(new Response("User registered !",201));
//    }

//    @PostMapping("/admin")
//    public ResponseEntity<Response> admin(){
//        return ResponseEntity.status(HttpStatus.OK).body(new Response("Welcome back ADMIN",200));
//    }
//
//    @PostMapping("/user")
//    public ResponseEntity<Response> user(){
//        return ResponseEntity.status(HttpStatus.OK).body(new Response("Welcome back user",200));
//    }


//    @PostMapping("/login")
//    public ResponseEntity<Response> login(){
//        return ResponseEntity.status(HttpStatus.OK).body(new Response("Welcome back",200));
//    }

    // All
    @PostMapping("/login")
    public ResponseEntity login(){
        return ResponseEntity.status(200).body(new ApiResponse("Logged in successfully"));
    }

    // Admin
    @GetMapping("/all-users")
    public ResponseEntity getUsers(){
        return ResponseEntity.status(200).body(authService.getAllUsers());
    }

    // Admin
    @GetMapping("/user/{id}")
    public ResponseEntity getUserById(@PathVariable Integer id){
        return ResponseEntity.status(200).body(authService.getUser(id));
    }

    // All
    @GetMapping("/my-user")
    public ResponseEntity getMyUser(@AuthenticationPrincipal MyUser auth){
        return ResponseEntity.status(200).body(authService.getUser(auth.getId()));
    }

    // All
    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody MyUser newUser){
        authService.addUser(newUser);
        return ResponseEntity.status(201).body(new ApiResponse("User Created"));
    }

    // User - No one can update another user
    @PutMapping("/update")
    public ResponseEntity updateUser(@RequestBody @Valid MyUser newUser, @AuthenticationPrincipal MyUser auth){
        authService.updateUser(newUser , auth.getId());
        return ResponseEntity.status(200).body(new ApiResponse("User Updated"));
    }

    // Admin
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id){
        authService.deleteUser(id);
        return ResponseEntity.status(200).body(new ApiResponse("User Deleted"));
    }
}

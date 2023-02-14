package com.example.hw27.Controller;

import com.example.hw27.DTO.ApiResponse;
import com.example.hw27.Model.MyUser;
import com.example.hw27.Service.MyUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class MyUserController {
    private final MyUserService myUserService;

    @GetMapping("/all-users")
    public ResponseEntity getUsers(){
        return ResponseEntity.status(200).body(myUserService.getAllUsers());
    }
    @GetMapping("/my-user")
    public ResponseEntity getMyUser(@AuthenticationPrincipal MyUser auth){
        return ResponseEntity.status(200).body(myUserService.getUser(auth.getId()));
    }
    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody MyUser newUser){
        myUserService.addUser(newUser);
        return ResponseEntity.status(201).body(new ApiResponse("User register successfully"));
    }
    @PutMapping("/update")
    public ResponseEntity updateUser(@RequestBody @Valid MyUser newUser, @AuthenticationPrincipal MyUser myUser){
        myUserService.updateUser(newUser , myUser.getId());
        return ResponseEntity.status(200).body(new ApiResponse("User Updated"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id){
        myUserService.deleteUser(id);
        return ResponseEntity.status(200).body(new ApiResponse("User Deleted"));
    }
}

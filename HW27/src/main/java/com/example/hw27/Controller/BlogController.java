package com.example.hw27.Controller;

import com.example.hw27.DTO.ApiResponse;
import com.example.hw27.Model.Blog;
import com.example.hw27.Model.MyUser;
import com.example.hw27.Service.BlogService;
import com.example.hw27.Service.MyUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/blog")
public class BlogController {
    private  final BlogService blogService;
    private  final MyUserService myUserService;

    //get all blog
    @GetMapping("/all-blog")
    public ResponseEntity getAllBlog(){
        return ResponseEntity.status(200).body(blogService.getAllBlog());
    }


    //add blog
    @PostMapping("/add-blog")
    public ResponseEntity addTodo(@RequestBody @Valid Blog blog, @AuthenticationPrincipal MyUser myUser){
        blogService.addBlog(blog,myUser.getId());
        return ResponseEntity.status(201).body(new ApiResponse("Blog Added"));
    }
    //update blog
    @PutMapping("/update/{id}")
    public ResponseEntity updateBlog(@RequestBody @Valid Blog blog, @PathVariable Integer id, @AuthenticationPrincipal MyUser myUser){
        blogService.updateBlog(id,blog,myUser.getId());
        return ResponseEntity.status(200).body(new ApiResponse("Blog Updated"));
    }

    //delete blog
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteBlog(@PathVariable Integer id, @AuthenticationPrincipal MyUser myUser){
        blogService.deleteBlog(id,myUser.getId());
        return ResponseEntity.status(200).body(new ApiResponse("Blog deleted"));
    }

    //get all user blog
    @GetMapping("/my-blogs")
    public ResponseEntity getMyTodos(@AuthenticationPrincipal MyUser myUser){
        return ResponseEntity.status(200).body(blogService.getAllUserBlog(myUser.getId()));
    }
    //get blog by id
    @GetMapping("/{id}")
    public ResponseEntity getBlogById(@PathVariable Integer id , @AuthenticationPrincipal MyUser myUser){
        return ResponseEntity.status(200).body(blogService.getBlogById(id , myUser.getId()));
    }
    //get blog by title
    @GetMapping("/title/{title}")
    public ResponseEntity getBlogByTitle(@PathVariable String title,@AuthenticationPrincipal MyUser myUser){
        return ResponseEntity.status(200).body(blogService.getBlogByTitle(title, myUser.getId()));
    }



}

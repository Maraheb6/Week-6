package com.example.hw27.Repository;

import com.example.hw27.Model.Blog;
import com.example.hw27.Model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Integer> {
    Blog findBlogById(Integer id);
    List<Blog>findAllByMyUser(MyUser myUser);
    Blog findBlogByTitle(String title);
}

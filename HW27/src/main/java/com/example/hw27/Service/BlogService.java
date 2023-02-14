package com.example.hw27.Service;

import com.example.hw27.ApiException.ApiException;
import com.example.hw27.Model.Blog;
import com.example.hw27.Model.MyUser;
import com.example.hw27.Repository.BlogRepository;
import com.example.hw27.Repository.MyUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
    private  final BlogRepository blogRepository;
    private final MyUserRepository myUserRepository;

    //get all blog
    public List<Blog> getAllBlog() {
        return blogRepository.findAll();
    }

    //add blog
    public void addBlog(Blog blog, Integer user){
        MyUser myUser=myUserRepository.findMyUserById(user);
        blog.setMyUser(myUser);

        blogRepository.save(blog);
    }

    //update blog
    public void updateBlog(Integer id , Blog blog , Integer user){
        Blog oldBlog=blogRepository.findBlogById(id);
        MyUser myUser=myUserRepository.findMyUserById(user);
        if (oldBlog==null){
            throw new ApiException("Blog not found");
        }else if(oldBlog.getMyUser().getId()!=user){
            throw new ApiException("Sorry , You do not have the authority to update this Blog!");
        }
        blog.setId(id);
        blog.setBody(blog.getBody());
        blog.setTitle(blog.getTitle());
        blog.setMyUser(myUser);
        blogRepository.save(blog);
    }
    //delete Blog
    public void deleteBlog(Integer id, Integer user){
        Blog blog=blogRepository.findBlogById(id);
        if (blog==null){
            throw new ApiException("Blog not found");
        }else if(blog.getMyUser().getId()!=user){
            throw new ApiException("Sorry , You do not have the authority to delete this Blog!");
        }

        blogRepository.delete(blog);
    }
// get all user blog
    public List <Blog>getAllUserBlog(Integer user){
        MyUser myUser=myUserRepository.findMyUserById(user);
        List<Blog> blogs=blogRepository.findAllByMyUser(myUser);
        if (blogs.isEmpty()){
            throw new ApiException("Empty!");
        }
        return blogs;
    }
    // get blog by id

    public Blog getBlogById(Integer blog_id , Integer user_id){
        Blog blog=blogRepository.findBlogById(blog_id);
        if (blog==null){
            throw new ApiException("Blog not Found");
        }
        if (blog.getMyUser().getId()!=user_id){
            throw new ApiException("Sorry , You do not have the authority to get this Blog!");
        }
        return blog;
    }

    //get blog by title
    public Blog getBlogByTitle(String title,Integer user_id){
        Blog blog=blogRepository.findBlogByTitle(title);
        if(blog==null){
            throw new ApiException("Blog Not Found");
        }
        if(blog.getMyUser().getId()!=user_id){
            throw new ApiException("Sorry , You do not have the authority to get this Blog!");

        }
        return blog;
    }
}

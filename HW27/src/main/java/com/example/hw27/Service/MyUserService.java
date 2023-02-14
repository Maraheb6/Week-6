package com.example.hw27.Service;

import com.example.hw27.ApiException.ApiException;
import com.example.hw27.Model.MyUser;
import com.example.hw27.Repository.MyUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyUserService {
    public final MyUserRepository myUserRepository;

    //get all users
    public List<MyUser> getAllUsers(){
        return myUserRepository.findAll();
    }

    //get user
    public MyUser getUser(Integer id){
        MyUser myUser=myUserRepository.findMyUserById(id);
        if (myUser==null){
            throw new ApiException("User Not Found!");
        }
        return myUser;
    }
    //add user
    public void addUser(MyUser newUser){
        newUser.setRole("USER");
        String hashedPassword=new BCryptPasswordEncoder().encode(newUser.getPassword());
        newUser.setPassword(hashedPassword);
        myUserRepository.save(newUser);
    }

    //update user
    public void updateUser(MyUser myUser, Integer id){
        MyUser oldUser=myUserRepository.findMyUserById(id);

        oldUser.setId(id);
        oldUser.setUsername(myUser.getUsername());
        oldUser.setPassword(myUser.getPassword());
      myUser.setPassword(new BCryptPasswordEncoder().encode(myUser.getPassword()));

        myUserRepository.save(myUser);
    }

    //delete user
    public void deleteUser(Integer id){
        MyUser myUser=myUserRepository.findMyUserById(id);
        if(myUser==null){
            throw new ApiException("User Not Found");
        }
        myUserRepository.delete(myUser);
    }
}

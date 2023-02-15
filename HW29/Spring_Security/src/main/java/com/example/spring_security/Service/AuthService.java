package com.example.spring_security.Service;

import com.example.spring_security.ApiException.ApiException;
import com.example.spring_security.Model.MyUser;
import com.example.spring_security.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;


//    public void register(MyUser myUser) {
//        myUser.setRole("USER");
//        String hashedPassword=new BCryptPasswordEncoder().encode(myUser.getPassword());
//        myUser.setPassword(hashedPassword);
//        authRepository.save(myUser);
//    }



    public List<MyUser> getAllUsers(){
        return authRepository.findAll();
    }

    public MyUser getUser(Integer id){
        MyUser myUser=authRepository.findMyUserById(id);
        if (myUser==null){
            throw new ApiException("User Not Found!");
        }
        return myUser;
    }


    public void addUser(MyUser newUser){
        newUser.setRole("USER");
        String hashedPassword=new BCryptPasswordEncoder().encode(newUser.getPassword());
        newUser.setPassword(hashedPassword);
        authRepository.save(newUser);
    }

    public void deleteUser(Integer id){
        MyUser myUser=authRepository.findMyUserById(id);
        if(myUser==null){
            throw new ApiException("User Not Found");
        }
        authRepository.delete(myUser);
    }


    public void updateUser(MyUser newUser, Integer id){
        MyUser oldUser=authRepository.findMyUserById(id);

        newUser.setId(id);
        newUser.setRole(oldUser.getRole());
        newUser.setPassword(new BCryptPasswordEncoder().encode(newUser.getPassword()));

        authRepository.save(newUser);
    }
}

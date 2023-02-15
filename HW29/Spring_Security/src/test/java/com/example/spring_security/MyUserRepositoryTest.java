package com.example.spring_security;

import com.example.spring_security.Model.MyUser;
import com.example.spring_security.Model.Todo;
import com.example.spring_security.Repository.AuthRepository;
import com.example.spring_security.Repository.TodoRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MyUserRepositoryTest {
    @Autowired
    AuthRepository authRepository;
    TodoRepository todoRepository;
    MyUser myUser;


    @BeforeEach
    void setUp() {

        myUser =new MyUser(null,"Muna","123","Admin",null );

    }
    @Test
    public void findMyUserByIdTest(){
        authRepository.save(myUser);
        MyUser myUser1=authRepository.findMyUserById(myUser.getId());
        Assertions.assertThat(myUser1).isEqualTo(myUser);
    }
@Test
    public void findMyUserByUsernameTest(){
        authRepository.save(myUser);
        MyUser myUser1=authRepository.findMyUserByUsername(myUser.getUsername());
        Assertions.assertThat(myUser1).isEqualTo(myUser);
    }
}

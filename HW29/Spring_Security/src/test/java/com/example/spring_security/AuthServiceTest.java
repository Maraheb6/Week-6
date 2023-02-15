package com.example.spring_security;

import com.example.spring_security.Model.MyUser;
import com.example.spring_security.Model.Todo;
import com.example.spring_security.Repository.AuthRepository;
import com.example.spring_security.Service.AuthService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {
    @InjectMocks
     AuthService authService;
    @Mock
    AuthRepository authRepository;

    MyUser myUser1,myUser2,myUser3;

    List<MyUser> myUsers;


    @BeforeEach
    void setUp() {

        myUser1 =new MyUser(null,"Muna","123","Admin",null );
        myUser2 =new MyUser(null,"Mai","123","Admin",null );
        myUser3 =new MyUser(null,"Maha","123","Admin",null );

        myUsers=new ArrayList<>();
        myUsers.add(myUser1);
        myUsers.add(myUser2);
        myUsers.add(myUser3);



    }
    @Test
    public void getAllUsersTest(){
        when(authRepository.findAll()).thenReturn(myUsers);
        List<MyUser>myUserList=authService.getAllUsers();
        Assertions.assertEquals(3,myUserList.size());
        verify(authRepository,times(1)).findAll();
    }

    @Test
    public void getUserTest(){
        when(authRepository.findMyUserById(myUser1.getId())).thenReturn(myUser1);
        MyUser myUser=authService.getUser(myUser1.getId());
        verify(authRepository,times(1)).findMyUserById(myUser1.getId());
    }
    @Test
    public void addUserTest(){
        authService.addUser(myUser3);
        verify(authRepository,times(1)).save(myUser3);
    }
@Test
    public void deleteUserTest(){
        when(authRepository.findMyUserById(myUser1.getId())).thenReturn(myUser1);
        authService.deleteUser(myUser1.getId());
        verify(authRepository,times(1)).findMyUserById(myUser1.getId());
        verify(authRepository,times(1)).delete(myUser1);
    }

    @Test
    public void updateUserTest(){
        when(authRepository.findMyUserById(myUser1.getId())).thenReturn(myUser1);
        authService.updateUser(myUser2, myUser1.getId());
        verify(authRepository,times(1)).findMyUserById(myUser1.getId());
        verify(authRepository,times(1)).save(myUser2);

    }
}

package com.example.spring_security;

import com.example.spring_security.Model.MyUser;
import com.example.spring_security.Model.Todo;
import com.example.spring_security.Repository.AuthRepository;
import com.example.spring_security.Repository.TodoRepository;
import com.example.spring_security.Service.TodoService;
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
public class TodoServiceTest {
 @InjectMocks
 TodoService todoService;
 @Mock
 TodoRepository todoRepository;
 @Mock
 AuthRepository authRepository;

    Todo todo1,todo2,todo3;
    MyUser myUser;

    List<Todo>todos;

    @BeforeEach
    void setUp() {
        myUser=new MyUser(null,"Maha" , "12345" , "ADMIN" , null);
        todo1 = new Todo(null , "todo1", "body1" , myUser );
        todo2 = new Todo(null , "todo2", "body2" , myUser );
        todo3 = new Todo(null , "todo3", "body3" , myUser );

        todos=new ArrayList<>();
        todos.add(todo1);
        todos.add(todo2);
        todos.add(todo3);
    }
    @Test
    public void getAllTodoTest(){
        when(todoRepository.findAll()).thenReturn(todos);
        List<Todo>todoList=todoService.getAllTodo();
        Assertions.assertEquals(3,todoList.size());
        verify(todoRepository,times(1)).findAll();
    }
@Test
    public void getMyTodosTest(){
        when(authRepository.findMyUserById(myUser.getId())).thenReturn(myUser);
        when(todoRepository.findAllByMyUser(myUser)).thenReturn(todos);

        List<Todo> todoList=todoService.getMyTodos(myUser.getId());
         Assertions.assertEquals(todoList,todos);
         verify(authRepository,times(1)).findMyUserById(myUser.getId());
         verify(todoRepository,times(1)).findAllByMyUser(myUser);

    }
    @Test
    public void addTodoTest(){
        when(authRepository.findMyUserById(myUser.getId())).thenReturn(myUser);
        todoService.addTodo(todo3, myUser.getId());
        verify(authRepository,times(1)).findMyUserById(myUser.getId());
        verify(todoRepository,times(1)).save(todo3);
    }
    @Test
    public void updateTodoTest(){
        when(todoRepository.findTodoById(todo1.getId())).thenReturn(todo1);
        when(authRepository.findMyUserById(myUser.getId())).thenReturn(myUser);
        todoService.updateTodo(todo1.getId(),todo2,myUser.getId());

        verify(todoRepository,times(1)).findTodoById(todo1.getId());
        verify(authRepository,times(1)).findMyUserById(myUser.getId());
        verify(todoRepository,times(1)).save(todo2);
    }

    @Test
    public  void deleteTodoTest(){
        when(todoRepository.findTodoById(todo1.getId())).thenReturn(todo1);

        todoService.deleteTodo(todo1.getId(),myUser.getId());

        verify(todoRepository,times(1)).findTodoById(todo1.getId());
        verify(todoRepository,times(1)).delete(todo1);
    }
    @Test
    public void getTodoByIdTest(){
        when(todoRepository.findTodoById(todo1.getId())).thenReturn(todo1);
        todoService.getTodoById(todo1.getId(),myUser.getId());

        verify(todoRepository,times(1)).findTodoById(todo1.getId());

    }

}

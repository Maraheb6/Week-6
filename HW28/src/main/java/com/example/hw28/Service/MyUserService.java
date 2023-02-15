package com.example.hw28.Service;

import com.example.hw28.ApiException.ApiException;
import com.example.hw28.Model.MyUser;
import com.example.hw28.Model.Orders;
import com.example.hw28.Model.Product;
import com.example.hw28.Repository.MyUserRepository;
import com.example.hw28.Repository.OrdersRepository;
import com.example.hw28.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyUserService {
    private final MyUserRepository myUserRepository;

    private final OrdersRepository ordersRepository;
    private final ProductRepository productRepository;


    //Get all Users
    public List<MyUser> getUser() {
        return myUserRepository.findAll();
    }

    //Add new User ...register
    public void register(MyUser myUser) {
        myUser.setRole("CUSTOMER");
    String hashedPassword = new BCryptPasswordEncoder().encode(myUser.getPassword());
        myUser.setPassword(hashedPassword);
        myUserRepository.save(myUser);
}

    //Update User
    public void updateUser(MyUser user){
        MyUser oldUser=myUserRepository.findMyUserById(user.getId());


        oldUser.setUsername(user.getUsername());
        oldUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        myUserRepository.save(oldUser);

    }
    //Delete User
    public void deleteUser(Integer ID){
        MyUser oldUser=myUserRepository.findMyUserById(ID);
        if(oldUser==null){
           throw new ApiException("User Not Found");
        }
        myUserRepository.delete(oldUser);

    }
    //get all customer order
    public List<Orders>getAllOrders(Integer id){
   MyUser myUser=myUserRepository.findMyUserById(id);
       List<Orders> orders=ordersRepository.findAllByMyUserId(myUser.getId());
       if(myUser==null){
           throw new ApiException("User not found");
       }
       return orders;
    }

//get customer by id
    public MyUser getCustomerById(Integer Id){
        MyUser myUser=myUserRepository.findMyUserById(Id);
        if(myUser==null){
            throw new ApiException("User Not Found");
        }
        return myUser;

    }

    //get my order
    public List getMyOrders(Integer id){
        MyUser myUser=myUserRepository.findMyUserById(id);
        List orders=ordersRepository.findAllByMyUser(myUser);
        if (orders.isEmpty()){
            throw new ApiException("Empty!");
        }
        return orders;
    }

}

package com.example.hw28.Service;

import com.example.hw28.ApiException.ApiException;
import com.example.hw28.Model.MyUser;
import com.example.hw28.Model.Orders;
import com.example.hw28.Model.Product;
import com.example.hw28.Repository.MyUserRepository;
import com.example.hw28.Repository.OrdersRepository;
import com.example.hw28.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersService {
    private  final OrdersRepository ordersRepository;

    private  final MyUserRepository myUserRepository;

    private  final ProductRepository productRepository;
    //Get all Orders..admin
    public List<Orders> getOrders(){
        return ordersRepository.findAll();
    }


    //Add new Orders..customer
    public void addOrders(Orders orders,Integer id,Integer user_id){
        Product product=productRepository.findProductById(id);
        MyUser myUser=myUserRepository.findMyUserById(user_id);
        if(product==null){
            throw new ApiException("Product not found");
        }
        orders.setStatus("new");

        orders.setTotalPrice(orders.getQuantity()* product.getPrice());

        orders.setMyUser(myUser);
        ordersRepository.save(orders);
    }

    //Update Orders status only admin
    public boolean updateOrders(Integer ID,Orders orders){
        Orders oldOrders=ordersRepository.findOrdersById(ID);
        if(oldOrders==null){
            return false;
        }

        oldOrders.setId(ID);
        oldOrders.setStatus(orders.getStatus());

        ordersRepository.save(oldOrders);
        return true;
    }
    //Delete Orders..customer

    public void deleteOrders(Integer ID,Integer user_id){
        Orders orders=ordersRepository.findOrdersById(ID);
        MyUser myUser=myUserRepository.findMyUserById(user_id);

        if(orders==null){
            throw new ApiException("Order Not Found");
        }
        if(orders.getMyUser().getId()!=user_id){
            throw new ApiException("Sorry , You do not have the authority to delete this Order");
        }
        if (orders.getStatus().equals("inProgress")||orders.getStatus().equals("completed"))
        {
            throw new ApiException("Sorry, Can't delete ");
        }
        ordersRepository.delete(orders);

    }

    //assign order to customer ,one to many..admin
    public void assignOrderToCustomer(Integer user_id,Integer order_id){
        MyUser myUser=myUserRepository.findMyUserById(user_id);
        Orders orders=ordersRepository.findOrdersById(order_id);
        if(myUser==null||orders==null){
            throw new ApiException("Wrong data ");
        }
        orders.setMyUser(myUser);
        ordersRepository.save(orders);
    }

    //assign order to product ,one to many..admin
    public void assignOrderToProduct(Integer product_id,Integer order_id){
        Product product=productRepository.findProductById(product_id);
        Orders orders=ordersRepository.findOrdersById(order_id);
        if(product==null||orders==null){
            throw new ApiException("Wrong data ");
        }
        orders.setProduct(product);
        ordersRepository.save(orders);
    }

//get order by id
    public Orders getOrderById(Integer id,Integer user_id) {
        Orders orders = ordersRepository.findOrdersById(id);
        if (orders == null) {
            throw new ApiException("order not found");
        }
        if (orders.getMyUser().getId() != user_id) {
            throw new ApiException("Sorry , You do not have the authority to get this Todo!");

        }
        return orders;
    }



}

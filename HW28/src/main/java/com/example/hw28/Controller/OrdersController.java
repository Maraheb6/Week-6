package com.example.hw28.Controller;

import com.example.hw28.ApiException.ApiException;
import com.example.hw28.Model.MyUser;
import com.example.hw28.Model.Orders;
import com.example.hw28.Service.OrdersService;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
public class OrdersController {
    private final OrdersService ordersService;

    //Get all Orders
    @GetMapping("/get")
    public ResponseEntity getOrders(){
        List<Orders> orders=ordersService.getOrders();
        return ResponseEntity.status(200).body(orders);
    }
    //Add new Orders
    @PostMapping("/add/{product_id}")
    public ResponseEntity addOrders(@Valid @RequestBody Orders orders,  @PathVariable Integer product_id,@AuthenticationPrincipal MyUser myUser){
       ordersService.addOrders(orders,product_id, myUser.getId());
        return ResponseEntity.status(200).body("Orders Added");
    }
    //Update Orders status only admin
    @PutMapping("/update/{ID}")
    public ResponseEntity updateOrders(@PathVariable Integer ID,@Valid@RequestBody Orders orders){
        boolean isUpdate= ordersService.updateOrders(ID,orders);
        if(isUpdate) {
            return ResponseEntity.status(200).body("Order Updated");
        }
        throw new ApiException("Wrong Order Id");
    }
    //Delete Orders
    @DeleteMapping("/delete/{ID}")
    public  ResponseEntity deleteOrders(@PathVariable Integer ID,@AuthenticationPrincipal MyUser myUser){
       ordersService.deleteOrders(ID, myUser.getId());
            return ResponseEntity.status(200).body("Order Deleted");
        }



    //assign Order to Customer,one to many
    @PutMapping("/{user_id}/order/{order_id}")
    public ResponseEntity assignOrderToCustomer(@PathVariable Integer user_id,@PathVariable Integer order_id){
        ordersService.assignOrderToCustomer(user_id,order_id);
        return ResponseEntity.status(200).body("Assignment Don Successfully");
    }
    //assign Order to Product,one to many
    @PutMapping("/{product_id}/product/{order_id}")
    public ResponseEntity assignOrderToProduct(@PathVariable Integer product_id,@PathVariable Integer order_id){
        ordersService.assignOrderToProduct(product_id,order_id);
        return ResponseEntity.status(200).body("Assignment Don Successfully");
    }

    //get order by id
    @GetMapping("get-order/{id}")
    private  ResponseEntity getOrderById(@PathVariable Integer id , @AuthenticationPrincipal MyUser myUser){
        return ResponseEntity.status(200).body(ordersService.getOrderById(id,myUser.getId()));
    }

}

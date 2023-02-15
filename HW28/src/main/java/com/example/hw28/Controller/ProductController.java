package com.example.hw28.Controller;

import com.example.hw28.ApiException.ApiException;
import com.example.hw28.Model.MyUser;
import com.example.hw28.Model.Product;
import com.example.hw28.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")

public class ProductController {
    private final ProductService productService;
    //Get all Product
    @GetMapping("/get")
    public ResponseEntity getProduct(){
        List<Product> products=productService.getProduct();
        return ResponseEntity.status(200).body(products);
    }
    //Add new Product
    @PostMapping("/add")
    public ResponseEntity addProduct(@Valid @RequestBody Product product ){
        productService.addProduct(product);
        return ResponseEntity.status(200).body("Product Added");
    }
    //Update Product
    @PutMapping("/update/{ID}")
    public ResponseEntity updateProduct(@PathVariable Integer ID,@Valid@RequestBody Product product){
        boolean isUpdate= productService.updateProduct(ID,product);
        if(isUpdate) {
            return ResponseEntity.status(200).body("Product Updated");
        }
        throw new ApiException("Wrong Product Id");
    }
    //Delete Product
    @DeleteMapping("/delete/{ID}")
    public  ResponseEntity deleteProduct(@PathVariable Integer ID){
        boolean isDelete= productService.deleteProduct(ID);
        if(isDelete) {
            return ResponseEntity.status(200).body("Product Deleted");
        }
        throw new ApiException("Wrong Product Id");
    }

    //get product by id
    @GetMapping("get-product/{id}")
    private  ResponseEntity getProductById(@PathVariable Integer id){
        return ResponseEntity.status(200).body(productService.getProductById(id));
    }
}

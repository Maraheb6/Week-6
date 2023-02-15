package com.example.hw28.Service;

import com.example.hw28.ApiException.ApiException;
import com.example.hw28.Model.Orders;
import com.example.hw28.Model.Product;
import com.example.hw28.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    //Get all Product
    public List<Product> getProduct(){
        return productRepository.findAll();
    }

    //Add new Product
    public void addProduct(Product product){
        productRepository.save(product);
    }

    //Update Product
    public boolean updateProduct(Integer ID,Product product){
        Product oldProduct=productRepository.findProductById(ID);
        if(oldProduct==null){
            return false;
        }

        //


        oldProduct.setId(ID);
        oldProduct.setName(product.getName());
        oldProduct.setPrice(product.getPrice());

        productRepository.save(oldProduct);
        return true;
    }
    //Delete Product
    public boolean deleteProduct(Integer ID){
        Product oldProduct=productRepository.findProductById(ID);
        if(oldProduct==null){
            return false;
        }
        productRepository.delete(oldProduct);
        return true;
    }

    //get product by id
    public Product getProductById(Integer ID){
        Product product=productRepository.findProductById(ID);
        if(product==null){
            throw new ApiException("Product Not Found");
        }
        return product;
    }

}

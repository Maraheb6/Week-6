package com.example.hw28.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "name should not be empty")
    private String name;

    @NotNull(message = " price should not be empty")
    private Integer price;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "product")
    private List<Orders>orders;
}

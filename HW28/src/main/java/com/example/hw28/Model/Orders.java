package com.example.hw28.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "quantity should not be null")
    private Integer quantity;

    private Integer totalPrice;

    Date dateReceived=new Date();
    @Pattern(regexp = "^(new||inProgress||completed)$",message = "status should be :new or inProgress or completed only ")
    private  String status;


    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    @JsonIgnore
    private MyUser myUser;

    @ManyToOne
    @JoinColumn(name = "order_id",referencedColumnName = "id")
    @JsonIgnore
    private Product product;
}

package com.example.hw27.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "title should not be empty")
    @Column(nullable = false)
    private String title;
    @NotNull(message = "body should not be empty")
    @Column(nullable = false)
    private String body;
    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    @JsonIgnore
    private MyUser myUser;
}

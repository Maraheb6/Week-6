package com.example.spring_security.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @NotNull
    private String title;

    @Column(nullable = false)
    @NotNull
    private String body;

    @ManyToOne
    @JoinColumn(name = "user_id" , referencedColumnName = "id")
    @JsonIgnore
    private MyUser myUser;
}
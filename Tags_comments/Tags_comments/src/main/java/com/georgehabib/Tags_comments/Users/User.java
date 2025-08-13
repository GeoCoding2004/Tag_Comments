package com.georgehabib.Tags_comments.Users;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "users") 
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false) 
    @NotBlank(message = "Username must not be blank")
    private String username;


    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;



}

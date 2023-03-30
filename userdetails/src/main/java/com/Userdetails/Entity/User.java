package com.Userdetails.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class User
{    @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String userName;
    private String firstName;
    private String lastName;

    private String password;

    private String role;
}

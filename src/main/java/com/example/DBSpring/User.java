package com.example.DBSpring;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    private Integer id;
    private String email;
    private String password;
    @Column(name = "full_name")
    private String fullName;

    public User(Integer id, String email, String password, String fullName) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
    }
    public User() {

    }
    public User(UserDTO userDTO){
        this.id = userDTO.getId();
        this.email = userDTO.getEmail();
        this.password = userDTO.getPassword();
        this.fullName = userDTO.getFullName();
    }
}

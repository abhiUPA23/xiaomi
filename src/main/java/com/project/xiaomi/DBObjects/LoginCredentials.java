package com.project.xiaomi.DBObjects;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "login_credentials")
public class LoginCredentials {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    int id;
    Integer skUid;
    String email;
    String password;

}

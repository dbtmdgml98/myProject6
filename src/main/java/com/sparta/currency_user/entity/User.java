package com.sparta.currency_user.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "user")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(length = 100)
    private String name;

    @Email
    @NotNull
    @Column(length = 100)
    private String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public User() {}

    // 부모=유저(User), 자식=환전(UserCurrency) -> `orphanRemoval = true`는 부모 엔티티를 삭제하면 자식 엔티티도 삭제한다.
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserCurrency> userCurrencies = new ArrayList<>();

}
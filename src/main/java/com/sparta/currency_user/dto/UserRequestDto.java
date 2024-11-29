package com.sparta.currency_user.dto;

import com.sparta.currency_user.entity.User;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UserRequestDto {

    @NotNull
    @Column(length = 100)
    private String name;

    @Email
    @NotNull
    @Column(length = 100)
    private String email;

    public User toEntity() {
        return new User(
                this.name,
                this.email
        );
    }
}

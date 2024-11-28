package com.sparta.currency_user.dto;

import com.sparta.currency_user.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserResponseDto {
    private Long id;

    private String name;
    private String email;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.createdDate = user.getCreatedDate();
        this.updatedDate = user.getUpdatedDate();
    }

    public UserResponseDto(Long id, String name, String email, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public static UserResponseDto toDto(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedDate(),
                user.getUpdatedDate()
        );
    }
}

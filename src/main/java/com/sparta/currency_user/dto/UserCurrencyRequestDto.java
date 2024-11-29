package com.sparta.currency_user.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UserCurrencyRequestDto {

    @NotNull
    private Long amountInKrw;
}

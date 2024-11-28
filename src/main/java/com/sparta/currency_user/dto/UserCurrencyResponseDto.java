package com.sparta.currency_user.dto;

import com.sparta.currency_user.entity.UserCurrency;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class UserCurrencyResponseDto {

    private Long userCurrencyId;
    private Long userId;
    private Long currencyId;
    private Long amountInKrw;
    private BigDecimal amountAfterExchange;
    private String status;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public UserCurrencyResponseDto(Long userCurrencyId, Long userId, Long currencyId, Long amountInKrw, BigDecimal amountAfterExchange, String status, LocalDateTime createdDate, LocalDateTime updatedDate) {

        this.userCurrencyId = userCurrencyId;
        this.userId = userId;
        this.currencyId = currencyId;
        this.amountInKrw = amountInKrw;
        this.amountAfterExchange = amountAfterExchange;
        this.status = status;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    // UserCurrency타입을 UserCurrencyResponseDto타입으로 변환
    public static UserCurrencyResponseDto toDto(UserCurrency savedUserCurrency) {
        return new UserCurrencyResponseDto(
                savedUserCurrency.getId(),
                savedUserCurrency.getUser().getId(),
                savedUserCurrency.getCurrency().getId(),
                savedUserCurrency.getAmountInKrw(),
                savedUserCurrency.getAmountAfterExchange(),
                savedUserCurrency.getStatus(),
                savedUserCurrency.getCreatedDate(),
                savedUserCurrency.getUpdatedDate()
        );
    }
}

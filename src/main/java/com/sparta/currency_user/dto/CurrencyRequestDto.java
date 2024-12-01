package com.sparta.currency_user.dto;

import com.sparta.currency_user.entity.Currency;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CurrencyRequestDto {

    @NotNull(message = "통화 이름은 필수입니다.")
    @Column(length = 50)
    private String currencyName;

    @NotNull(message = "환율은 필수입니다.")
    private BigDecimal exchangeRate;

    @NotNull(message = "통화 표시는 필수입니다.")
    @Column(length = 50)
    private String symbol;

    public Currency toEntity() {
        return new Currency(
                this.currencyName,
                this.exchangeRate,
                this.symbol
        );
    }
}

package com.sparta.currency_user.dto;

import com.sparta.currency_user.entity.Currency;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CurrencyRequestDto {

    @NotNull
    @Column(length = 50)
    private String currencyName;

    @NotNull
    private BigDecimal exchangeRate;

    @NotNull
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

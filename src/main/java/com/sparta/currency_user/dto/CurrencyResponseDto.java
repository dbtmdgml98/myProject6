package com.sparta.currency_user.dto;

import com.sparta.currency_user.entity.Currency;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class CurrencyResponseDto {
    private Long id;

    private String currencyName;
    private BigDecimal exchangeRate;
    private String symbol;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public CurrencyResponseDto(Currency currency) {
        this.id = currency.getId();
        this.currencyName = currency.getCurrencyName();
        this.exchangeRate = currency.getExchangeRate();
        this.symbol = currency.getSymbol();
        this.createdDate = currency.getCreatedDate();
        this.updatedDate = currency.getUpdatedDate();
    }

    public CurrencyResponseDto(Long id, String currencyName, BigDecimal exchangeRate, String symbol, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.id = id;
        this.currencyName = currencyName;
        this.exchangeRate = exchangeRate;
        this.symbol = symbol;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public static CurrencyResponseDto toDto(Currency currency) {
        return new CurrencyResponseDto(
            currency.getId(),
            currency.getCurrencyName(),
            currency.getExchangeRate(),
            currency.getSymbol(),
            currency.getCreatedDate(),
            currency.getUpdatedDate()
        );
    }
}

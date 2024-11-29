package com.sparta.currency_user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;

@Entity
@Getter
@Table(name = "user_currency")
public class UserCurrency extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "currency_id")
    private Currency currency;

    @NotNull
    private Long amountInKrw;   // 환전 전 금액

    @NotNull
    private BigDecimal amountAfterExchange; // 횐전 후 금액

    @NotNull
    @Column(length = 50)
    private String status;  // 상태 (normal, cancelled)

    public UserCurrency() {}

    public UserCurrency(User user, Currency currency, Long amountInKrw, BigDecimal amountAfterExchange, String status) {
        this.user = user;
        this.currency = currency;
        this.amountInKrw = amountInKrw;
        this.amountAfterExchange = amountAfterExchange;
        this.status = status;
    }

    public void updateStatus(String status) {
        this.status = status;
    }
}

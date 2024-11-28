package com.sparta.currency_user.repository;

import com.sparta.currency_user.entity.UserCurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface UserCurrencyRepository extends JpaRepository<UserCurrency, Long> {

    // 특정 userId로 환전 요청 전체 조회
    List<UserCurrency> findAllByUserId(Long userId);
}

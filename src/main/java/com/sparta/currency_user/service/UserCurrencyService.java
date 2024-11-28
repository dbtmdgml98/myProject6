package com.sparta.currency_user.service;

import com.sparta.currency_user.dto.UserCurrencyRequestDto;
import com.sparta.currency_user.dto.UserCurrencyResponseDto;
import com.sparta.currency_user.entity.Currency;
import com.sparta.currency_user.entity.User;
import com.sparta.currency_user.entity.UserCurrency;
import com.sparta.currency_user.repository.CurrencyRepository;
import com.sparta.currency_user.repository.UserCurrencyRepository;
import com.sparta.currency_user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserCurrencyService {

    private final UserRepository userRepository;
    private final UserCurrencyRepository userCurrencyRepository;
    private final CurrencyRepository currencyRepository;


    public UserCurrencyResponseDto exchange(Long currencyId, Long userId, UserCurrencyRequestDto userCurrencyRequestDto) {
        // 환전 전 금액, 환율, 유저
        Long amountInKrw = userCurrencyRequestDto.getAmountInKrw();
        Currency currency = currencyRepository.findById(currencyId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT,"통화 아이디가 존재하지 않습니다."));
        User user = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "유저 아이디가 존재하지 않습니다."));

        // Long -> BigDecimal 타입 변환
        BigDecimal bigDecimalAmountInKrw = BigDecimal.valueOf(amountInKrw);
        // 환전 후 금액 = 환전 전 금액 / 환율 (소수점 두번째까지 반올림)
        BigDecimal amountAfterExchange = bigDecimalAmountInKrw.divide(currency.getExchangeRate(), 2, RoundingMode.HALF_UP);

        UserCurrency userCurrency = new UserCurrency(
                user,
                currency,
                amountInKrw,
                amountAfterExchange,
                "normal"
        );
        // userCurrency에 저장
        UserCurrency savedUserCurrency = userCurrencyRepository.save(userCurrency);

        return UserCurrencyResponseDto.toDto(savedUserCurrency);
    }

    public List<UserCurrencyResponseDto> findExchangeInformationById(Long userId) {

        List<UserCurrency> userCurrencyList = userCurrencyRepository.findAllByUserId(userId);

        return userCurrencyList.stream().map(UserCurrencyResponseDto::toDto).toList();
    }
}

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
        Currency findCurrency = currencyRepository.findById(currencyId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT,"통화 아이디가 존재하지 않습니다."));
        User findUser = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "유저 아이디가 존재하지 않습니다."));

        // Long -> BigDecimal 타입 변환
        BigDecimal bigDecimalAmountInKrw = BigDecimal.valueOf(amountInKrw);
        // 환전 후 금액 = 환전 전 금액 / 환율 (소수점 두번째까지 반올림)
        BigDecimal amountAfterExchange = bigDecimalAmountInKrw.divide(findCurrency.getExchangeRate(), 2, RoundingMode.HALF_UP);

        // UserCurrency 객체 생성
        UserCurrency userCurrency = new UserCurrency(
                findUser,
                findCurrency,
                amountInKrw,
                amountAfterExchange,
                "normal"
        );

        // UserCurrency 객체를 DB에 저장
        UserCurrency savedUserCurrency = userCurrencyRepository.save(userCurrency);

        // UserCurrency타입을 UserCurrencyResponseDto타입으로 변환 후 반환
        return UserCurrencyResponseDto.toDto(savedUserCurrency);
    }

    public List<UserCurrencyResponseDto> findExchangeInformationsById(Long userId) {

        // 환전 요청 리스트 DB에서 조회
        List<UserCurrency> findUserCurrencyList = userCurrencyRepository.findAllByUserId(userId);

        // 리스트를 쪼개서 UserCurrency타입에서 UserCurrencyResponseDto타입으로 변환 후 리스트 형태로 반환
        return findUserCurrencyList.stream().map(UserCurrencyResponseDto::toDto).toList();
    }

    public UserCurrencyResponseDto updateExchangeStatus(Long id) {

        // DB에서 요청한 id에 맞는 UserCurrency 조회
        UserCurrency findUserCurrency = userCurrencyRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "유저 아이디가 존재하지 않습니다."));

        // id가 존재하면 조회된 객체의 Status를 cancelled로 수정
//        UserCurrency updatedUserCurrency = userCurrencyRepository.updateUserCurrencyByIdAndStatus(findUserCurrency.getId(), "cancelled");
        findUserCurrency.updateStatus("cancelled");

        // 수정 정보 DB에 저장
        userCurrencyRepository.save(findUserCurrency);

        return UserCurrencyResponseDto.toDto(findUserCurrency);
    }
}

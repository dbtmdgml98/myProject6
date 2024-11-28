package com.sparta.currency_user.controller;

import com.sparta.currency_user.dto.UserCurrencyRequestDto;
import com.sparta.currency_user.dto.UserCurrencyResponseDto;
import com.sparta.currency_user.service.UserCurrencyService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exchanges")
@RequiredArgsConstructor
public class UserCurrencyController {

    private final UserCurrencyService userCurrencyService;

    // C: 환전 요청 수행
    @PostMapping
    public ResponseEntity<UserCurrencyResponseDto> exchange(@RequestParam(value = "currencyId") Long currencyId,
                                                            @RequestParam(value = "userId") Long userId,
                                                            @RequestBody UserCurrencyRequestDto userCurrencyRequestDto) {

        UserCurrencyResponseDto exchanged = userCurrencyService.exchange(currencyId, userId, userCurrencyRequestDto);

        return new ResponseEntity<>(exchanged, HttpStatus.CREATED);
    }

    // R: 고객 고유 식별자를 기반으로 특정 고객이 수행한 환전 요청 조회

    // U: 특정 환전 요청 상태를 취소로 변경

    // D: 고객이 삭제될 때 해당 고객이 수행한 모든 환전 요청도 삭제

}

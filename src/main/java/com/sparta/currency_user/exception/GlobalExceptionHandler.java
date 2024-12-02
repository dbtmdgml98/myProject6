package com.sparta.currency_user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice   // REST API에서 발생한 예외를 JSON 형식으로 처리
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {

        // 긴 예외메세지 결과들을 가져와서 그 중 에러필드들만 꺼낸 후 하나씩 쪼개서 필요한 메세지만 뽑아서 리스트 형태로 바꾼다.
        List<String> errorMessages = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + " : " + error.getDefaultMessage())
                .collect(Collectors.toList());

        // Map 형태로 반환하기 위해 생성하고 키와 값들을 넣어준다.
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("errorCode", "ERR001");
        responseBody.put("errorMessage", "요청값의 형식이 맞지 않습니다.");
        responseBody.put("details", errorMessages);

        return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Map<String, Object>> handleMissingServletRequestParameterExceptions(MissingServletRequestParameterException ex) {

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("errorCode", "ERR001");
        responseBody.put("errorMessage", "요청값에 맞는 데이터가 존재하지 않습니다.");
        responseBody.put("details", ex.getMessage());

        return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
    }

}
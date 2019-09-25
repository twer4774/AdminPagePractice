package com.walter.AdminPagePractice.controller.model.network;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Header<T> {

    //api의 통신시간
    private LocalDateTime transactionTime;

    //api의 응답코드
    private String resulteCode;

    //api의 부가설명
    private String description;

    //api의 데이터
    private T data;

    //OK일 때
    public static <T> Header<T> OK(){
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .resulteCode("OK")
                .description("Result is OK")
                .build();
    }

    //DATA가 들어간 OK
    public static <T> Header<T> OK(T data){
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .resulteCode("OK")
                .description("Result is OK with data")
                .data(data)
                .build();
    }

    //ERROR
    public static <T> Header<T> ERROR(String description){
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .resulteCode("ERROR")
                .description(description)
                .build();
    }
}

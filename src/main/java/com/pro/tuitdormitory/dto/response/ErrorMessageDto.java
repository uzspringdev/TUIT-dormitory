package com.pro.tuitdormitory.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ErrorMessageDto {
    private String errorMessage;
    private Integer errorCode;

    public ErrorMessageDto(String errorMessage, Integer errorCode) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }
}

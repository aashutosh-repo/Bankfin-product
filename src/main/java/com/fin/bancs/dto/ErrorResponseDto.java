package com.fin.bancs.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class ErrorResponseDto {
    private String apiPath;
    private HttpStatus errCode;
    private String errMessage;
    private LocalDateTime errTime;
}

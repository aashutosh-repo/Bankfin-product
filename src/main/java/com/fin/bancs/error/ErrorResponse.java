package com.fin.bancs.error;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Data
public class ErrorResponse {
	private String path;
    private HttpStatus errorCode;
    private String errorDescription;
    private LocalDateTime timestamp;    
    
}
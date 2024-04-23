package com.microservice.accountService.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(name = "Errors", description = "Exception response to the Errors")
public class ErrorResponseDto {

	@Schema(name = "APIs path", example = "/home/api")
	private String apiPath;
	
	@Schema(name = "HTTP error code", example = "200")
	private HttpStatus errorCode;
	
	@Schema(name = "Error Message", example = "Internal Server Error")
	private String errorMessage;
	
	@Schema(name = "Error Time")
	private LocalDateTime errorTime;
	
}

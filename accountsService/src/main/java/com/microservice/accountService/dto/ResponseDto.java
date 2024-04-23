package com.microservice.accountService.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Responses", description = "Response back to the CLients")
public class ResponseDto {

	@Schema(name = "Http Status Code", example = "200")
	private String statusCode;
	
	@Schema(name = "Https Status Messages", example = "Updated Successfully")
	private String statusMsg;
	
}

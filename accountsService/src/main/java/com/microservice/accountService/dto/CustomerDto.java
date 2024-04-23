package com.microservice.accountService.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(
		name = "Customer",
		description = "input Customer and Account info")
public class CustomerDto {

	@Schema(name = "Name of the customer",
			example = "Bharat")
	@NotEmpty(message = "can't be empty")
	@Size(min = 4, max = 20, message = "Name should be between 4 to 20 characters")
	private String name;
	
	@Schema(name = "Email of the customer", example = "bharat@gmail.com")
	@NotEmpty(message = "can't be empty")
	@Email(message = "please put a valid email")
	private String email;
	
	@Schema(name = "MobileNo of the customer", example = "1234567890")
	@NotEmpty(message = "can't be empty")
	@Pattern(regexp = "(^$|[0-9]{10})", message = "Should have a 10 digit mobile number")
	private String mobileNumber;
	
	private AccountsDto accountsDto;
}

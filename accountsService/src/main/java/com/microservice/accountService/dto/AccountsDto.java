package com.microservice.accountService.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(
		name = "Account",
		description = "input for Account info")
public class AccountsDto {
	
	@Schema(name = "Account No of the customer", example = "1234")
	@NotEmpty(message = "can't be empty")
	@Pattern(regexp = "(^$|[0-9]{1-10})", message = "Should be a digit and between 1-9")
	private int accountNo;
	
	@Schema(name = "Type of the Account", example = "Savings")
	@NotEmpty(message = "can't be empty")
	private String accountType;
	
	@Schema(name = "Branch Address of the Bank", example = "Gurgaon, 122001")
	@NotEmpty(message = "can't be empty")
	private String branchAddress;

}

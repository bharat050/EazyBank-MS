package com.microservice.accountService.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.accountService.constants.AccountConstant;
import com.microservice.accountService.dto.CustomerDto;
import com.microservice.accountService.dto.ErrorResponseDto;
import com.microservice.accountService.dto.ResponseDto;
import com.microservice.accountService.service.impl.ImplAccountService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "accounts", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Validated
@Tag(name = "Accounts related CRUD APIs",
description = "It has 4 APIs can perform Create, Update, Read and Delete the data from the DB")
public class AccountsController {
	
	ImplAccountService iAccountService;

	@Operation(
			summary = "Creating Account",
			description = "Create Account of customer by taking its personal info.")
	@ApiResponse(
			responseCode = "201",
			description = "Created Successfully")
	@PostMapping("create")
	public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto){
		iAccountService.createAccount(customerDto);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(new ResponseDto(AccountConstant.STATUS_201, AccountConstant.MESSAGE_201));
	}
	@Operation(
			summary = "Fetching Account",
			description = "Fetching Customer account and its personla info using mobile no.")
	@GetMapping("fetch")
	public ResponseEntity<CustomerDto> fetchCustomerData(
			@Pattern(regexp = "(^$|[0-9]{10})", message = "Should have a 10 digit mobile number")
			@RequestParam("mobileNumber") String mobileNo){
		
		CustomerDto dto = iAccountService.fetchCustomer(mobileNo);
		return new ResponseEntity<>(dto, HttpStatus.FOUND);
	}
	@Operation(
			summary = "Updating Account/Customer Info",
			description = "Fetching Customer account and its personla info using mobile no. and updating as per user input")
	@ApiResponses({
			@ApiResponse(
					responseCode = "200",
					description = "Updated Successfully"
			),
			@ApiResponse(
					responseCode = "500",
					description = "Internal_Server_error",
					content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
			)
	})
	@PutMapping("update")
	public ResponseEntity<ResponseDto> updateCustomersInfo(@Valid @RequestBody CustomerDto customerDto){
		if(iAccountService.updateCustomerInfo(customerDto)) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDto(AccountConstant.STATUS_200, AccountConstant.MESSAGE_200));
		}
		return new ResponseEntity<>(new ResponseDto(AccountConstant.STATUS_500,AccountConstant.MESSAGE_500)
				, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@Operation(
			summary = "Deleting Account/Customer Info",
			description = "Deleting Customer account and its personal info using customer_Id")
	@ApiResponses({
			@ApiResponse(
					responseCode = "200",
					description = "Updated Successfully"
			)
	})
	@DeleteMapping("delete/{id}")
	public ResponseEntity<ResponseDto> deleteCustomerRecord(@PathVariable("id") int customerId){
		iAccountService.deleteCustomerRecord(customerId);
		
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(new ResponseDto(AccountConstant.STATUS_200, AccountConstant.MESSAGE_200));
	}
}

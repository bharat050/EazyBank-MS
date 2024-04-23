package com.microservice.accountService.mapper;

import com.microservice.accountService.dto.AccountsDto;
import com.microservice.accountService.entity.AccountsEntity;

public class AccountMapper {

	public static AccountsEntity mapToAccount(AccountsDto accountsDto) {
		return AccountsEntity.builder()
				.accountType(accountsDto.getAccountType())
				.branchAddress(accountsDto.getBranchAddress())
				.accountNo(accountsDto.getAccountNo()).build();
	}
	
	public static AccountsDto mapToDto(AccountsEntity accountsEntity) {
		return AccountsDto.builder()
				.accountNo(accountsEntity.getAccountNo())
				.accountType(accountsEntity.getAccountType())
				.branchAddress(accountsEntity.getBranchAddress()).build();
	}
}

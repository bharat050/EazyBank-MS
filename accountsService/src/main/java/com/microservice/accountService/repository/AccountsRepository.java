package com.microservice.accountService.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.microservice.accountService.entity.AccountsEntity;
import com.microservice.accountService.entity.CustomerEntity;
import java.util.List;


@Repository
public interface AccountsRepository extends JpaRepository<AccountsEntity, Integer>{

	Optional<AccountsEntity> findByCustomerId(int customer_id);
	
	@Transactional
	@Modifying
	void deleteByCustomerId(int customerId);
	
}

package com.microservice.loanService.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.loanService.entity.LoansEntity;

public interface LoansRepository extends JpaRepository<LoansEntity, Integer>{

	Optional<LoansEntity> findByMobileNumber(String mobileNumber);

	Optional<LoansEntity> findByLoanNumber(String loanNumber);

}

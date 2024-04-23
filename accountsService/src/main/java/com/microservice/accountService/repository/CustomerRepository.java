package com.microservice.accountService.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.accountService.entity.CustomerEntity;
import java.util.Optional;


@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer>{

	Optional<CustomerEntity> findByMobileNumber(String mobileNumber);
}
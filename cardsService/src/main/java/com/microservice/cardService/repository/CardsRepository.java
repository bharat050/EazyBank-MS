package com.microservice.cardService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.cardService.entity.CardEntity;

import java.util.Optional;

@Repository
public interface CardsRepository extends JpaRepository<CardEntity, Integer> {

    Optional<CardEntity> findByMobileNumber(String mobileNumber);

    Optional<CardEntity> findByCardNumber(String cardNumber);

}
package com.microservice.cardService.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Cards")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CardEntity extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cardId;
	
	private String mobileNumber;
	
	private String cardNumber;
	
	private String cardType;

	private int totalLimit;

	private int amountUsed;

	private int availableAmount;
}

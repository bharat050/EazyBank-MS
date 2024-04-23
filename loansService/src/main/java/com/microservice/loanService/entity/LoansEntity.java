package com.microservice.loanService.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "Loans")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoansEntity extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int loanId;
	
	private String mobileNumber;
	
	private String loanNumber;
	
	private String loanType;
	
	private int totalLoan;
	
	private int amountPaid;
	
	private int outstandingAmount;
	
	
}

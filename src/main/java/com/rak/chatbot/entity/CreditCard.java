package com.rak.chatbot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CREDIT_CARD")
public class CreditCard {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;

	@Column(name = "CREDIT_CARD_NUMBER")
	private String creditCardNumber;

	@Column(name = "ACCOUNT_NUMBER")
	private String account_Number;

	@Column(name = "MOBILE_NUMBER")
	private String mobileNumber;

	@Column(name = "BALANCE_AMOUNT")
	private Long balanceAmount;

	@Column(name = "MIN_AMOUNT_DUE")
	private Long minAmountDue;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public String getAccount_Number() {
		return account_Number;
	}

	public void setAccount_Number(String account_Number) {
		this.account_Number = account_Number;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Long getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(Long balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public Long getMinAmountDue() {
		return minAmountDue;
	}

	public void setMinAmountDue(Long minAmountDue) {
		this.minAmountDue = minAmountDue;
	}

}

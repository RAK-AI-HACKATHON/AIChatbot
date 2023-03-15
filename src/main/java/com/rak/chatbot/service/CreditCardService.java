package com.rak.chatbot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rak.chatbot.entity.CreditCard;
import com.rak.chatbot.repository.CreditCardRepository;

@Service
public class CreditCardService {

	@Autowired
	CreditCardRepository creditCardRepository;
	
	public String checkBalance(String creditCardNo) {
		Optional<CreditCard> creditCard = creditCardRepository.findByCreditCardNumber(creditCardNo);
		if(creditCard.isPresent()) {
			Long balanceAmount = creditCard.get().getBalanceAmount();
			return ("CreditCard Balance Amount is " + balanceAmount.toString());
		}
		return "Kindly enter the valid Credit Card Number.";
	}

	public String checkMinimumAmountToBePaid(String creditCardNo) {
		Optional<CreditCard> creditCard = creditCardRepository.findByCreditCardNumber(creditCardNo);
		if(creditCard.isPresent()) {
			Long minimumAmountToBePaid = creditCard.get().getMinAmountDue();
			return ("Minimum Amount to be paid is " + minimumAmountToBePaid.toString());
		}
		return "Kindly enter the valid Credit Card Number.";
	}

}

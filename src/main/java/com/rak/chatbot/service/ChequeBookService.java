package com.rak.chatbot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rak.chatbot.entity.ChequeBook;
import com.rak.chatbot.repository.ChequeBookRepository;

@Service
public class ChequeBookService {
	
	@Autowired
	ChequeBookRepository chequeBookRepository;

	public String checkStatus(String chequeBookReqNo) {
		String status = null;
		Optional<ChequeBook> chequeBookReq = chequeBookRepository.findByRequestNumber(chequeBookReqNo);
		if(chequeBookReq.isPresent()) {
			 status = chequeBookReq.get().getStatus();
			return "Cheque Book status is " + status;
		}else { //If requesNumber search fails, searching with Account number, as input we got from User might be an account Number 
			String accountNumber = chequeBookReqNo;
			Optional<ChequeBook> chequeBookReqbyAcctNumber = chequeBookRepository.findByAccountNumber(accountNumber);
			if(chequeBookReqbyAcctNumber.isPresent()) {
				 status = chequeBookReqbyAcctNumber.get().getStatus();
				 return "Cheque Book status is " + status;
			}
		}
		return "Invalid Cheque Book Request Number. Kindly enter the valid number.";
	}

}

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
		Optional<ChequeBook> chequeBookReq = chequeBookRepository.findByRequestNumber(chequeBookReqNo);
		if(chequeBookReq.isPresent()) {
			String status = chequeBookReq.get().getStatus();
			return "Cheque Book status is " + status;
		}
		return "Invalid Cheque Book Request Number. Kindly enter the valid number.";
	}

}

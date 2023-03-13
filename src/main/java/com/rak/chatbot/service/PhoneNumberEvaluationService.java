package com.rak.chatbot.service;

import org.springframework.stereotype.Service;

@Service
public class PhoneNumberEvaluationService {

	public String evaluatePhoneNumber(String mobileNumber) {
		return "Thank you for the information. Kindly enter the OTP sent to your registered mobile number.";
		
	}
}

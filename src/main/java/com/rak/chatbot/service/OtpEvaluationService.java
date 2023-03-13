package com.rak.chatbot.service;

import org.springframework.stereotype.Service;

@Service
public class OtpEvaluationService {
	
	public String evaluateOTP(Integer otp) {
		if(otp == 1234) {
			return "Mobile number validated successfully.. Please let me know how can I help you..?";
		}
		return "Invalid OTP";
	}

}

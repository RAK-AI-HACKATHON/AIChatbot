package com.rak.chatbot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rak.chatbot.entity.Customer;
import com.rak.chatbot.repository.CustomerRepository;

@Service
public class PhoneNumberEvaluationService {

	@Autowired
	CustomerRepository customerRepository;

	/*
	 * @Autowired PhoneNumberUtil phoneNumberUtil;
	 */

	String number = null;

	public String evaluatePhoneNumber(String mobileNumber) {

		String formattedMobileNo = formatMobileNumber(mobileNumber);
		if (null != formattedMobileNo) {
			Optional<Customer> customer = customerRepository.findByMobileNumber(formattedMobileNo);
			if (customer.isPresent())
				return "Thank you for the information. Kindly enter the OTP sent to your registered mobile number.";
		}

		return "Customer details not found. Please enter the registered mobile number";
	}

	public String formatMobileNumber(String mobileNumber) {

		String mobileno = mobileNumber.trim();
		String number = null;

		if (mobileNumber.trim().length() >= 10) {
			if (mobileno.startsWith("+971-")) {
				number = mobileno.substring(5);
			} else if (mobileno.startsWith("+971")) {
				number = mobileno.substring(4);
			} else if (mobileno.startsWith("971")) {
				number = mobileno.substring(3);
			} else if (mobileno.startsWith("(0")) {
				number = mobileno.substring(4);
			} else if (mobileNumber.startsWith("(")) {
				number = mobileno.substring(6);
			} else if (mobileNumber.startsWith("0")) {
				number = mobileno.substring(1);
			}
		} else if (mobileNumber.length() == 9) {
			number = mobileno;
		}
		if (null != number && number.length() == 9) {
			mobileno = "0" + number.trim();
			return mobileno;
		}
		return null;
	}

	/*
	 * private boolean isValidMobileNumber(String mobileNumber) { // PhoneNumber
	 * number = new PhoneNumber(); phoneNumberUtil.isPossibleNumber(mobileNumber,
	 * "US"); // phoneNumberUtil.isPossibleNumber(number); return false; }
	 */
}

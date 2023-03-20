package com.rak.chatbot.controller;

import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.client.json.JsonGenerator;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.dialogflow.v3.model.GoogleCloudDialogflowV2IntentMessage;
import com.google.api.services.dialogflow.v3.model.GoogleCloudDialogflowV2IntentMessageText;
import com.google.api.services.dialogflow.v3.model.GoogleCloudDialogflowV2WebhookRequest;
import com.google.api.services.dialogflow.v3.model.GoogleCloudDialogflowV2WebhookResponse;
import com.rak.chatbot.service.ChequeBookService;
import com.rak.chatbot.service.CreditCardService;
import com.rak.chatbot.service.OtpEvaluationService;
import com.rak.chatbot.service.PhoneNumberEvaluationService;

@RestController
public class RakBankAIChatBotController {

	private final JacksonFactory jacksonFactory = new JacksonFactory();

	@Autowired
	PhoneNumberEvaluationService phoneNumberEvaluationService;

	@Autowired
	OtpEvaluationService otpEvaluationService;

	@Autowired
	ChequeBookService chequeBookService;
	
	@Autowired
	CreditCardService creditCardService;
	

	@PostMapping("/webhookcallback")
	public String webhookcallback(@RequestBody String rawData) throws IOException {
		String responseString = null;
		System.out.println(rawData);
		// Step 1. Parse the request
		GoogleCloudDialogflowV2WebhookRequest request = jacksonFactory.createJsonParser(rawData)
				.parse(GoogleCloudDialogflowV2WebhookRequest.class);

		// Step 2. Process the request
		//System.out.println(request.getQueryResult().getParameters().get("phone-number"));
		//System.out.println(request.getQueryResult().getParameters().get("otp"));
		//System.out.println(request.getQueryResult().getParameters().get("cheque-book-req-no"));
		if (null != request.getQueryResult().getParameters().get("phone-number")) {
			responseString = phoneNumberEvaluationService
					.evaluatePhoneNumber((String) request.getQueryResult().getParameters().get("phone-number"));
		} else if (null != request.getQueryResult().getParameters().get("otp")) {
			Integer otpValue = ((BigDecimal) request.getQueryResult().getParameters().get("otp")).intValue();
			responseString = otpEvaluationService.evaluateOTP(otpValue);
		} else if (null != request.getQueryResult().getParameters().get("cheque-book-req-no")) {
			String chequeBookReqNo = (String) request.getQueryResult().getParameters().get("cheque-book-req-no");
			System.out.println(chequeBookReqNo);
			responseString = chequeBookService.checkStatus(chequeBookReqNo);
		} else if(null != request.getQueryResult().getParameters().get("check-balance")) {
			String creditCardNo = (String) request.getQueryResult().getParameters().get("check-balance");
			System.out.println(creditCardNo);
			responseString = creditCardService.checkBalance(creditCardNo);			
		}else if (null != request.getQueryResult().getParameters().get("check-minimum-amount")) {
			String creditCardNo = (String) request.getQueryResult().getParameters().get("check-minimum-amount");
			System.out.println(creditCardNo);
			responseString = creditCardService.checkMinimumAmountToBePaid(creditCardNo);		
		}
			  

		// Step 3. Build the response message
		GoogleCloudDialogflowV2IntentMessage msg = new GoogleCloudDialogflowV2IntentMessage();
		GoogleCloudDialogflowV2IntentMessageText text = new GoogleCloudDialogflowV2IntentMessageText();
		List<String> textList = new ArrayList<String>();
		textList.add(responseString);
		text.setText(textList);
		msg.setText(text);

		GoogleCloudDialogflowV2WebhookResponse response = new GoogleCloudDialogflowV2WebhookResponse();
		List<GoogleCloudDialogflowV2IntentMessage> msgList = new ArrayList<GoogleCloudDialogflowV2IntentMessage>();
		msgList.add(msg);

		response.setFulfillmentMessages(msgList);
		StringWriter stringWriter = new StringWriter();
		JsonGenerator jsonGenerator = jacksonFactory.createJsonGenerator(stringWriter);
		jsonGenerator.enablePrettyPrint();
		jsonGenerator.serialize(response);
		jsonGenerator.flush();
		return stringWriter.toString();
	}
}

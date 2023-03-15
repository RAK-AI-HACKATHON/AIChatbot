package com.rak.chatbot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rak.chatbot.entity.CreditCard;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {

	public Optional<CreditCard> findByCreditCardNumber(String creditCardNo);
}

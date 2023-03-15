package com.rak.chatbot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rak.chatbot.entity.ChequeBook;

@Repository
public interface ChequeBookRepository extends JpaRepository<ChequeBook, Long> {

	public Optional<ChequeBook> findByRequestNumber(String requestNumber);

}

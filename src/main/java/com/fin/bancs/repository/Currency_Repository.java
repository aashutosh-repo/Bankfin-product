package com.fin.bancs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fin.bancs.common.Currency;

import java.util.Optional;

@Repository
public interface Currency_Repository extends JpaRepository<Currency, String> {
    //Optional<Currency> findBycurrency_code(String currencyCode);
}


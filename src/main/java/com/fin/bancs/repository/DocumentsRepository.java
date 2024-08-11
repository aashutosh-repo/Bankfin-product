package com.fin.bancs.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fin.bancs.customer.DocumentsDetails;

@Repository
@Transactional
public interface DocumentsRepository extends JpaRepository<DocumentsDetails, Integer> {

}
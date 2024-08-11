package com.fin.bancs.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fin.bancs.customer.NomineeDetails;

@Repository
@Transactional
public interface Nominee_Repository extends JpaRepository<NomineeDetails, Integer>{

}

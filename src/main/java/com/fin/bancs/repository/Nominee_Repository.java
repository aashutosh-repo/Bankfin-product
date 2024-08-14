package com.fin.bancs.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fin.bancs.customer.NomineeDetails;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface Nominee_Repository extends JpaRepository<NomineeDetails, Integer>{
	List<NomineeDetails> findByOwnerId( int ownerId);
	Optional<NomineeDetails> findByNomineeRefNum(int nomineeRefNum);
}

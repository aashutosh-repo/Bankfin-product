package com.fin.bancs.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fin.bancs.BP.Documents_Details;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface Documents_Repository extends JpaRepository<Documents_Details, Integer> {

}

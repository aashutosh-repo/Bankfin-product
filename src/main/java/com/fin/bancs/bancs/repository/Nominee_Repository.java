package com.fin.bancs.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fin.bancs.BP.Nominee_Details;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface Nominee_Repository extends JpaRepository<Nominee_Details, Integer>{

}

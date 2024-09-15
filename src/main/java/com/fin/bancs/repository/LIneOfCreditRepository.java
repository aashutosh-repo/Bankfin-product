package com.fin.bancs.repository;

import com.fin.bancs.account.LineOfCredit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface LIneOfCreditRepository extends JpaRepository<LineOfCredit,Long> {
    Optional<LineOfCredit> findByShipmentId(String ShipmentId);

}

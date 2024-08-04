package com.fin.bancs.repository;

import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.fin.bancs.utils.Sequence;

@Repository
public interface SequenceRepository extends JpaRepository<Sequence, String> {

    Sequence findBySequenceName(String sequenceName);
    @Query(value = "SELECT MAX(seq_id) FROM Sequences", nativeQuery = true)
    Optional<Integer> findMaxSequenceId();
}

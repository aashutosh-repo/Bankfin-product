package com.fin.bancs.utils;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fin.bancs.repository.SequenceRepository;

@Service
public class SequenceGenerator {
  
  @Autowired
  private SequenceRepository sequenceRepository;
  
  public BigInteger generateSequence(String sequenceName) {
    Sequence sequence = sequenceRepository.findBySequenceName(sequenceName);
    if (sequence == null) {
      sequence = new Sequence(sequenceName, new BigInteger("1"),getNextSeqId());
      sequenceRepository.save(sequence);
    } else {
      sequence.setNextValue(sequence.getNextValue().add(new BigInteger("1")));
      sequenceRepository.save(sequence);
    }
    return sequence.getNextValue();
  }
  
  
  private int getNextSeqId() {
	    Optional<Integer> maxSeq = sequenceRepository.findMaxSequenceId();
	    int maxSequence =0;
	    if(maxSeq.isPresent()) {
	    	maxSequence = maxSeq.get()+1;
	    }
	    return maxSequence;

  }
  
}
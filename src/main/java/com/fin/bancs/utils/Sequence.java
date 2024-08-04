package com.fin.bancs.utils;

import java.math.BigInteger;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sequences")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Sequence {
  
  @Id
  @Column(name = "sequence_name")
  private String sequenceName;
  
  @Column(name = "next_value")
  private BigInteger nextValue;
  @Column(name = "seq_id")
  private int seqId;
}

//CREATE TABLE sequences (
//		  sequence_name VARCHAR(50) PRIMARY KEY,
//		  next_value BIGINT NOT NULL
//		);
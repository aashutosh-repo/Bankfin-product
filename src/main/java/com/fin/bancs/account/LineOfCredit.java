package com.fin.bancs.account;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity	
@Data
public class LineOfCredit {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long locId;
	 private int locType;
	    private Long contractId;
	    private String bankName;
	    private String locStatus;
	    private BigDecimal creditAmout;
}

package com.fin.bancs.transactions;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fin.bancs.audit.AuditInfo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
public class Cash_Reserve extends AuditInfo{
	private static final long serialVersionUID = 111L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private int blockId ;
	private String transactionId;
	private BigDecimal blockedAmount;
	private int blockStatus;
	private String blockReason;
	private String referenceId;
	private LocalDateTime startDate;
	private LocalDateTime expiryDate;
	private int isAutoBlocked;
	private String  linkedAccountId;

}

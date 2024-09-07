package com.fin.bancs.account;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

import com.fin.bancs.audit.AuditInfo;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
@ToString 
public class Account extends AuditInfo{
	@EmbeddedId
	protected AccountPk accountId; //pk
	private String internalAcntNumber;
	protected int account_status;
	protected String accountNumber;
	protected LocalDate account_open_dt;
	protected String currency;
	protected int custId;
	protected int cus_type;
	protected int npa_status;
	protected int min_bal;
	protected Date last_withdrawal_dt;
	protected BigDecimal available_balance;
	protected String owner_name;
	protected int atm_req_flag;
	protected int cheq_req_flag;
	protected int sms_req_flag;
	protected LocalDate clsr_dt;
	protected String clsr_reason;
}

package com.fin.bancs.account;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.TableGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
@ToString 
public class Account {
	@EmbeddedId
	protected AccountPk account_id; //pk
	protected int account_status;
	protected String account_number;
	protected LocalDate account_open_dt;
	protected int currency;
	protected int cust_id;
	protected int cus_type;
	protected int npa_status;
	protected int min_bal;
	protected Date last_withdrawal_dt;
	protected BigDecimal available_balance;
	protected int owner_name;
	protected int atm_req_flag;
	protected int cheq_req_flag;
	protected int sms_req_flag;
	protected LocalDate clsr_dt;
	protected String clsr_reason;
}

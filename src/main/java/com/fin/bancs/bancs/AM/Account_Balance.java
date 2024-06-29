package com.fin.bancs.AM;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Account_Balance {
	protected int ACCOUNT_ID; //PK
	protected int ACCOUNT_TYPE; //PK
	protected int BALANCE_SEQ_ID; //PK
	protected LocalDate BALANCE_DATE;
	protected int TXN_TYPE;
	protected long INTRST_RT;
	protected BigDecimal PRNCPL_BAL;
	protected BigDecimal INTRST_BAL;
	protected BigDecimal TOTAL_BAL;
	protected BigDecimal OPENING_BAL;
	protected BigDecimal AVAILABLE_BAL;
	protected LocalDate Last_Update;
}

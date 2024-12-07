package com.fin.bancs.constants;

public class AccountsConstants {
    private AccountsConstants() {
    	
        // restrict instantiation
    }
    
  //Account Types
	public static final int SAVINGS_ACCOUNT = 101;
	public static final int CURRENT_ACCOUNT = 102;
	public static final int INTERNAL_ACCOUNT = 103;
    public static final int CHECKING_ACCOUNT = 104;
    public static final int BUSINESS_ACCOUNT = 105;
    public static final int LOAN_ACCOUNT =201;
    public static final int TRADE_ACCOUNT = 202;
    public static final int INVESTMENT_ACCOUNT = 203;
	
    
    public static final String  SAVINGS = "Savings";
    public static final String  ADDRESS = "WhiteField, Bengalore. India";
    public static final String  STATUS_201 = "201";
    public static final String  MESSAGE_201 = "Account created successfully";
    public static final String  STATUS_202 = "202";
    public static final String  MESSAGE_202 = "Customer created successfully";
    public static final String  STATUS_200 = "200";
    public static final String  MESSAGE_200 = "Request processed successfully";
    public static final String  STATUS_417 = "417";
    public static final String  MESSAGE_417_UPDATE= "Update operation failed. Please try again or contact Dev team";
    public static final String  MESSAGE_417_DELETE= "Delete operation failed. Please try again or contact Dev team";
    public static final String  STATUS_500 = "500";
    public static final String  MESSAGE_500 = "An error occurred. Please try again or contact Dev team";
    public static final String  STATUS_501 = "501";
    public static final String  MESSAGE_501 = "There is a problem in processing Request";
    
    
    public static final String INSUFFICIENT_BALANCE = "Account Has Insufficient Balance";
    
    public static final int PENDING_AUTH = 1;
    public static final int MODIFY_ONHOLD = 2;
    public static final int ACTIVE = 3;

}

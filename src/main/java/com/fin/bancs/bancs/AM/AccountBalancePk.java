package com.fin.bancs.AM;

import java.io.Serializable;
import java.util.Objects;

public class AccountBalancePk implements Serializable{
	private int ACCOUNT_ID;
	protected int ACCOUNT_TYPE;
	protected int BALANCE_SEQ_ID;
	
	
	public int getACCOUNT_ID() {
		return ACCOUNT_ID;
	}
	public void setACCOUNT_ID(int aCCOUNT_ID) {
		ACCOUNT_ID = aCCOUNT_ID;
	}
	public int getACCOUNT_TYPE() {
		return ACCOUNT_TYPE;
	}
	public void setACCOUNT_TYPE(int aCCOUNT_TYPE) {
		ACCOUNT_TYPE = aCCOUNT_TYPE;
	}
	public int getBALANCE_SEQ_ID() {
		return BALANCE_SEQ_ID;
	}
	public void setBALANCE_SEQ_ID(int bALANCE_SEQ_ID) {
		BALANCE_SEQ_ID = bALANCE_SEQ_ID;
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(ACCOUNT_ID, ACCOUNT_TYPE, BALANCE_SEQ_ID);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccountBalancePk other = (AccountBalancePk) obj;
		return ACCOUNT_ID == other.ACCOUNT_ID && ACCOUNT_TYPE == other.ACCOUNT_TYPE
				&& BALANCE_SEQ_ID == other.BALANCE_SEQ_ID;
	}
	
	
}

package com.fin.bancs.AM;

import java.io.Serializable;
import java.util.Objects;

public class AccountPk implements Serializable{
	private int ACCOUNT_ID;
	private int ACCOUNT_TYPE;
	
	
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
	@Override
	public int hashCode() {
		return Objects.hash(ACCOUNT_ID, ACCOUNT_TYPE);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccountPk other = (AccountPk) obj;
		return ACCOUNT_ID == other.ACCOUNT_ID && ACCOUNT_TYPE == other.ACCOUNT_TYPE;
	}
	
	 
	

}

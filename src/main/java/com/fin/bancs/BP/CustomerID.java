package com.fin.bancs.BP;

import java.io.Serializable;
import java.util.Objects;
public class CustomerID  implements Serializable{
	private int CUS_ID;
	private int CUS_TYP;
	
	public int getCUS_ID() {
		return CUS_ID;
	}
	public void setCUS_ID(int cUST_ID) {
		CUS_ID = cUST_ID;
	}
	public int getCUS_TYP() {
		return CUS_TYP;
	}
	public void setCUS_TYP(int cUST_TYP) {
		CUS_TYP = cUST_TYP;
	}


	public CustomerID(){

	}
	public CustomerID(int CUS_ID, int CUS_TYP) {
		this.CUS_ID = CUS_ID;
		this.CUS_TYP = CUS_TYP;
	}

	@Override
	public int hashCode() {
		return Objects.hash(CUS_ID, CUS_TYP);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerID other = (CustomerID) obj;
		return CUS_ID == other.CUS_ID && CUS_TYP == other.CUS_TYP;
	}
}

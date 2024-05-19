package com.fin.bancs.common;

import java.io.Serializable;
import java.util.Objects;

public class GoldenCodePk implements Serializable{
	private int code_id;
	private int value;
	
	public GoldenCodePk (int code_id,int value) {
		this.code_id=code_id;
		this.value=value;
		
	}
	public int getCode_id() {
		return code_id;
	}
	public void setCode_id(int code_id) {
		this.code_id = code_id;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	@Override
	public int hashCode() {
		return Objects.hash(code_id, value);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GoldenCodePk other = (GoldenCodePk) obj;
		return code_id == other.code_id && value == other.value;
	}
	
	

}

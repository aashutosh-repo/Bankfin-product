package com.fin.bancs.common;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.util.Date;
@Entity
public class Golden_Codes {
	@Id
	private int code_id; //pk
	@Id
	private int value; //pk
	private String code_name;
	private String short_desc;
	private String Description;
	private Date insert_date ;
	private LocalDate Last_Update;
	protected String Userid;
	
	public Golden_Codes(int code_id, int value, String shortDesc, String description, 
			Date insert_date, String userid,LocalDate Last_Update) {
		super();
		this.code_id = code_id;
		this.value = value;
        this.short_desc = shortDesc;
        this.Description = description;
		this.insert_date = insert_date;
		Userid = userid;
		//This date should not Change and should always pick Current system date
		this.Last_Update=LocalDate.now();
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
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public Date getInsert_date() {
		return insert_date;
	}
	public void setInsert_date(Date insert_date) {
		this.insert_date = insert_date;
	}
	public String getUserid() {
		return Userid;
	}
	public void setUserid(String userid) {
		Userid = userid;
	}
	public String getCode_name() {
		return code_name;
	}
	public void setCode_name(String code_name) {
		this.code_name = code_name;
	}

	public String getShort_desc() {
		return short_desc;
	}

	public void setShort_desc(String short_desc) {
		this.short_desc = short_desc;
	}
	public LocalDate getLast_Update() {
		return Last_Update;
	}
	public void setLast_Update(LocalDate last_Update) {
		Last_Update = LocalDate.now();
	}
	
}

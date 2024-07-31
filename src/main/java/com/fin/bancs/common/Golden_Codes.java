package com.fin.bancs.common;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;
@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
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
	
}

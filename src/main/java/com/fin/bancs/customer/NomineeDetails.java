package com.fin.bancs.customer;

import java.time.LocalDate;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class Nominee_Details {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int nominee_id; //pk
	protected int owner_id;
	protected int owner_type;
	protected int seq_num;
	protected int nom_share;
	protected int nom_type; //maJOR/MINOR
	protected int nom_type_code;
	protected String nominee_first_name;
	protected String nominee_middle_name;
	protected String nominee_last_name;
	protected int rtln_type;
	protected int rtln_type_code;
	protected LocalDate date_of_birth;
	protected int nom_add_id;
	protected String nom_doc_id;
	protected int ver;
}

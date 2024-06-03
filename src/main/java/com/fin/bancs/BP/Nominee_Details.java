package com.fin.bancs.BP;

import java.time.LocalDate;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Nominee_Details {

	@Id
	@GeneratedValue(generator = "NOM_ID-seq-generator")
	@GenericGenerator(
			name = "NOM_ID-generator",
			parameters = {
					@Parameter(name = "DOC_ID_seq", value = "user_sequence"),
					@Parameter(name = "initial_value", value = "9999999"),
					@Parameter(name = "increment_size", value = "1")
			})
	protected int nominee_id; //pk
	protected int owner_id;
	protected int owner_type;
	protected int nom_type; //maJOR/MINOR
	protected int nom_type_code;
	protected String nominee_first_name;
	protected String nominee_middle_name;
	protected String nominee_last_name;
	protected int rtln_type;
	protected int rtln_type_code;
	protected LocalDate date_of_birth;
	protected LocalDate last_update;
	protected int nom_add_id;
	protected String nom_doc_id;
	protected int ver;

	public Nominee_Details() {
	}
	
	
	

	public Nominee_Details(int nominee_id, int owner_id, int owner_type, int nom_type, int nom_type_code,
			String nominee_first_name, String nominee_middle_name, String nominee_last_name, int rtln_type,
			int rtln_type_code, LocalDate date_of_birth, LocalDate last_update, int nom_add_id, String nom_doc_id,
			int ver) {
		super();
		this.nominee_id = nominee_id;
		this.owner_id = owner_id;
		this.owner_type = owner_type;
		this.nom_type = nom_type;
		this.nom_type_code = nom_type_code;
		this.nominee_first_name = nominee_first_name;
		this.nominee_middle_name = nominee_middle_name;
		this.nominee_last_name = nominee_last_name;
		this.rtln_type = rtln_type;
		this.rtln_type_code = rtln_type_code;
		this.date_of_birth = date_of_birth;
		this.last_update = last_update;
		this.nom_add_id = nom_add_id;
		this.nom_doc_id = nom_doc_id;
		this.ver = ver;
	}




	public int getNominee_id() {
		return nominee_id;
	}

	public void setNominee_id(int nominee_id) {
		this.nominee_id = nominee_id;
	}

	public int getOwner_id() {
		return owner_id;
	}

	public void setOwner_id(int owner_id) {
		this.owner_id = owner_id;
	}

	public int getOwner_type() {
		return owner_type;
	}

	public void setOwner_type(int owner_type) {
		this.owner_type = owner_type;
	}

	public int getNom_type() {
		return nom_type;
	}

	public void setNom_type(int nom_type) {
		this.nom_type = nom_type;
	}

	public int getNom_type_code() {
		return nom_type_code;
	}

	public void setNom_type_code(int nom_type_code) {
		this.nom_type_code = nom_type_code;
	}

	public String getNominee_first_name() {
		return nominee_first_name;
	}

	public void setNominee_first_name(String nominee_first_name) {
		this.nominee_first_name = nominee_first_name;
	}

	public String getNominee_middle_name() {
		return nominee_middle_name;
	}

	public void setNominee_middle_name(String nominee_middle_name) {
		this.nominee_middle_name = nominee_middle_name;
	}

	public String getNominee_last_name() {
		return nominee_last_name;
	}

	public void setNominee_last_name(String nominee_last_name) {
		this.nominee_last_name = nominee_last_name;
	}

	public int getRtln_type() {
		return rtln_type;
	}

	public void setRtln_type(int rtln_type) {
		this.rtln_type = rtln_type;
	}

	public int getRtln_type_code() {
		return rtln_type_code;
	}

	public void setRtln_type_code(int rtln_type_code) {
		this.rtln_type_code = rtln_type_code;
	}

	public LocalDate getDate_of_birth() {
		return date_of_birth;
	}

	public void setDate_of_birth(LocalDate date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

	public LocalDate getLast_update() {
		return last_update;
	}

	public void setLast_update(LocalDate last_update) {
		this.last_update = last_update;
	}

	public int getNom_add_id() {
		return nom_add_id;
	}

	public void setNom_add_id(int nom_add_id) {
		this.nom_add_id = nom_add_id;
	}

	public String getNom_doc_id() {
		return nom_doc_id;
	}

	public void setNom_doc_id(String nom_doc_id) {
		this.nom_doc_id = nom_doc_id;
	}

	public int getVer() {
		return ver;
	}

	public void setVer(int ver) {
		this.ver = ver;
	}




	@Override
	public String toString() {
		return "Nominee_Details [nominee_id=" + nominee_id + ", owner_id=" + owner_id + ", owner_type=" + owner_type
				+ ", nom_type=" + nom_type + ", nom_type_code=" + nom_type_code + ", nominee_first_name="
				+ nominee_first_name + ", nominee_middle_name=" + nominee_middle_name + ", nominee_last_name="
				+ nominee_last_name + ", rtln_type=" + rtln_type + ", rtln_type_code=" + rtln_type_code
				+ ", date_of_birth=" + date_of_birth + ", last_update=" + last_update + ", nom_add_id=" + nom_add_id
				+ ", nom_doc_id=" + nom_doc_id + ", ver=" + ver + "]";
	}
	


}

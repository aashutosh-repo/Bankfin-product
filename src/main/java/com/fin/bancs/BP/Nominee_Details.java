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
	protected int NOMINEE_ID; //pk
	protected int OWNER_ID;
	protected int OWNER_TYPE;
	protected int NOM_TYPE; //MAJOR/MINOR
	protected int NOM_TYPE_CODE;
	protected String NOMINEE_FIRST_NAME;
	protected String NOMINEE_MIDDLE_NAME;
	protected String NOMINEE_LAST_NAME;
	protected int RTLN_TYPE;
	protected int RTLN_TYPE_CODE;
	protected LocalDate DATE_OF_BIRTH;
	protected LocalDate LAST_UPDATE;
	protected int NOM_ADD_ID;
	protected int NOM_DOC_ID;

	public Nominee_Details() {
	}

	public Nominee_Details(int nOMINEE_ID, int oWNER_ID, int oWNER_TYPE, int nOM_TYPE, int nOM_TYPE_CODE,
			String nOMINEE_FIRST_NAME, String nOMINEE_MIDDLE_NAME, String nOMINEE_LAST_NAME, int rTLN_TYPE,
			int rTLN_TYPE_CODE, LocalDate dATE_OF_BIRTH, int nOM_ADD_ID, int nOM_DOC_ID, LocalDate LAST_UPDATE) {
		super();
		this.NOMINEE_ID = nOMINEE_ID;
		this.OWNER_ID = oWNER_ID;
		this.OWNER_TYPE = oWNER_TYPE;
		this.NOM_TYPE = nOM_TYPE;
		this.NOM_TYPE_CODE = nOM_TYPE_CODE;
		this.NOMINEE_FIRST_NAME = nOMINEE_FIRST_NAME;
		this.NOMINEE_MIDDLE_NAME = nOMINEE_MIDDLE_NAME;
		this.NOMINEE_LAST_NAME = nOMINEE_LAST_NAME;
		this.RTLN_TYPE = rTLN_TYPE;
		this.RTLN_TYPE_CODE = rTLN_TYPE_CODE;
		this.DATE_OF_BIRTH = dATE_OF_BIRTH;
		this.NOM_ADD_ID = nOM_ADD_ID;
		this.NOM_DOC_ID = nOM_DOC_ID;
		this.LAST_UPDATE = LAST_UPDATE;
	}



	public int getNOMINEE_ID() {
		return NOMINEE_ID;
	}

	public void setNOMINEE_ID(int nOMINEE_ID) {
		NOMINEE_ID = nOMINEE_ID;
	}

	public int getOWNER_ID() {
		return OWNER_ID;
	}

	public void setOWNER_ID(int oWNER_ID) {
		OWNER_ID = oWNER_ID;
	}

	public int getOWNER_TYPE() {
		return OWNER_TYPE;
	}

	public void setOWNER_TYPE(int oWNER_TYPE) {
		OWNER_TYPE = oWNER_TYPE;
	}

	public int getNOM_TYPE() {
		return NOM_TYPE;
	}

	public void setNOM_TYPE(int nOM_TYPE) {
		NOM_TYPE = nOM_TYPE;
	}

	public int getNOM_TYPE_CODE() {
		return NOM_TYPE_CODE;
	}

	public void setNOM_TYPE_CODE(int nOM_TYPE_CODE) {
		NOM_TYPE_CODE = nOM_TYPE_CODE;
	}

	public String getNOMINEE_FIRST_NAME() {
		return NOMINEE_FIRST_NAME;
	}

	public void setNOMINEE_FIRST_NAME(String nOMINEE_FIRST_NAME) {
		NOMINEE_FIRST_NAME = nOMINEE_FIRST_NAME;
	}

	public String getNOMINEE_MIDDLE_NAME() {
		return NOMINEE_MIDDLE_NAME;
	}

	public void setNOMINEE_MIDDLE_NAME(String nOMINEE_MIDDLE_NAME) {
		NOMINEE_MIDDLE_NAME = nOMINEE_MIDDLE_NAME;
	}

	public String getNOMINEE_LAST_NAME() {
		return NOMINEE_LAST_NAME;
	}

	public void setNOMINEE_LAST_NAME(String nOMINEE_LAST_NAME) {
		NOMINEE_LAST_NAME = nOMINEE_LAST_NAME;
	}

	public int getRTLN_TYPE() {
		return RTLN_TYPE;
	}

	public void setRTLN_TYPE(int rTLN_TYPE) {
		RTLN_TYPE = rTLN_TYPE;
	}

	public int getRTLN_TYPE_CODE() {
		return RTLN_TYPE_CODE;
	}

	public void setRTLN_TYPE_CODE(int rTLN_TYPE_CODE) {
		RTLN_TYPE_CODE = rTLN_TYPE_CODE;
	}

	public LocalDate getDATE_OF_BIRTH() {
		return DATE_OF_BIRTH;
	}

	public void setDATE_OF_BIRTH(LocalDate dATE_OF_BIRTH) {
		DATE_OF_BIRTH = dATE_OF_BIRTH;
	}

	public int getNOM_ADD_ID() {
		return NOM_ADD_ID;
	}

	public void setNOM_ADD_ID(int nOM_ADD_ID) {
		NOM_ADD_ID = nOM_ADD_ID;
	}

	public int getNOM_DOC_ID() {
		return NOM_DOC_ID;
	}

	public void setNOM_DOC_ID(int nOM_DOC_ID) {
		NOM_DOC_ID = nOM_DOC_ID;
	}	public LocalDate getLAST_UPDATE() {
		return LAST_UPDATE;
	}

	public void setLAST_UPDATE(LocalDate lAST_UPDATE) {
		LAST_UPDATE = LocalDate.now();
	}
}

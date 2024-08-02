package com.fin.bancs.account;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter @Setter @EqualsAndHashCode
public class AccountBalancePk implements Serializable{
	private int account_id;
	protected int account_type;
	protected int balance_seq_id;
	
}

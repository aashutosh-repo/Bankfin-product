package com.fin.bancs.account;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter @Setter @EqualsAndHashCode
@AllArgsConstructor @NoArgsConstructor
public class AccountBalancePk implements Serializable{
	private int account_id;
	protected int account_type;
	protected int balance_seq_id;
	
}

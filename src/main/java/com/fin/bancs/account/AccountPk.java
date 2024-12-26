package com.fin.bancs.account;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class AccountPk implements Serializable{
	private int account_id;
	private int account_type;
	
}

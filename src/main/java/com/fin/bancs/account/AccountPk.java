package com.fin.bancs.account;

import java.io.Serializable;
import java.util.Objects;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter @EqualsAndHashCode
public class AccountPk implements Serializable{
	private int account_id;
	private int account_type;
	
}

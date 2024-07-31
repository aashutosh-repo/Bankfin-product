package com.fin.bancs.customer;

import java.io.Serializable;


import java.util.Objects;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @EqualsAndHashCode
public class CustomerID  implements Serializable{
	private int CUS_ID;
	private int CUS_TYP;
}

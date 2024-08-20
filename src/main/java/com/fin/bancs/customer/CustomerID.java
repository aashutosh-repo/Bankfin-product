package com.fin.bancs.customer;

import java.io.Serializable;


import java.util.Objects;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter @Setter @EqualsAndHashCode
public class CustomerID  implements Serializable{
	private int customerID;
	private int customerType;
}


package com.fin.bancs.customer;

import java.io.Serializable;


import java.util.Objects;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter @Setter @EqualsAndHashCode
@AllArgsConstructor @NoArgsConstructor
public class CustomerID  implements Serializable{
	private int customerID;
	private int customerType;
}


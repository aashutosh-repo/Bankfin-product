package com.fin.bancs.customer;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Embeddable
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public  class AddressID implements Serializable {
    protected int customerId;
    protected int addressId;
}

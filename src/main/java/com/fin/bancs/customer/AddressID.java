package com.fin.bancs.customer;

import java.io.Serializable;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class Cust_Address_detailsPk implements Serializable {
    protected int PIN_CODE;
    protected int ADDRESS_ID;
}

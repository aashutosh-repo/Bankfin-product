package com.fin.bancs.common;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Currency {
	@Id
	private String currency_code;
	private String country;
	private String country_code;
	private String exch_currency;
	private BigDecimal exch_value;
}

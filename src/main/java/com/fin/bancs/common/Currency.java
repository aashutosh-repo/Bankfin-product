package com.fin.bancs.common;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Currency {
	@Id
	private String currency_code;
	private String country;
	private String country_code;
	private String exch_currency;
	private BigDecimal exch_value;
	private LocalDate last_update;
	public Currency(String currency_code, String country, String country_code, String exch_currency,
			BigDecimal exch_value, LocalDate last_update) {
		super();
		this.currency_code = currency_code;
		this.country = country;
		this.country_code = country_code;
		this.exch_currency = exch_currency;
		this.exch_value = exch_value;
		this.last_update = last_update;
	}
	public String getCurrency_code() {
		return currency_code;
	}
	public void setCurrency_code(String currency_code) {
		this.currency_code = currency_code;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCountry_code() {
		return country_code;
	}
	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}
	public String getExch_currency() {
		return exch_currency;
	}
	public void setExch_currency(String exch_currency) {
		this.exch_currency = exch_currency;
	}
	public BigDecimal getExch_value() {
		return exch_value;
	}
	public void setExch_value(BigDecimal exch_value) {
		this.exch_value = exch_value;
	}
	public LocalDate getLast_update() {
		return last_update;
	}
	public void setLast_update(LocalDate last_update) {
		this.last_update = last_update;
	}
	@Override
	public String toString() {
		return "Currency [currency_code=" + currency_code + ", country=" + country + ", country_code=" + country_code
				+ ", exch_currency=" + exch_currency + ", exch_value=" + exch_value + ", last_update=" + last_update
				+ "]";
	}
	
}

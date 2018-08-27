package com.bartolay.inventory.form;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

public class CurrencyForm {

	private Integer currency_id;

	@NotNull(message = "Name is required")
	@Size(min = 2, max = 100, message = "Currency Name is required. Length must be 2 to 100 characters.")
	private String currency_name;

	@NotNull(message = "Code is required")
	@Size(min = 3, max = 3, message = "Code must be 3 characters")
	private String currency_code;

	@NotNull(message = "Symbol is required")
	@Size(min = 1, max = 5, message = "Symbol length must be 1 to 5 characters.")
	private String currency_symbol;

	@NotNull(message = "Symbol is required")
	private BigDecimal currency_rate = new BigDecimal("0.0");

	@NotNull(message = "Decimal Places is required")
	@Range(min = 0, max = 10)
	private Integer currency_decimalPlaces = 2;

	public Integer getCurrency_id() {
		return currency_id;
	}

	public void setCurrency_id(Integer currency_id) {
		this.currency_id = currency_id;
	}

	public String getCurrency_name() {
		return currency_name;
	}

	public void setCurrency_name(String currency_name) {
		this.currency_name = currency_name;
	}

	public String getCurrency_code() {
		return currency_code;
	}

	public void setCurrency_code(String currency_code) {
		this.currency_code = currency_code;
	}

	public String getCurrency_symbol() {
		return currency_symbol;
	}

	public void setCurrency_symbol(String currency_symbol) {
		this.currency_symbol = currency_symbol;
	}

	public BigDecimal getCurrency_rate() {
		return currency_rate;
	}

	public void setCurrency_rate(BigDecimal currency_rate) {
		this.currency_rate = currency_rate;
	}

	public Integer getCurrency_decimalPlaces() {
		return currency_decimalPlaces;
	}

	public void setCurrency_decimalPlaces(Integer currency_decimalPlaces) {
		this.currency_decimalPlaces = currency_decimalPlaces;
	}

}

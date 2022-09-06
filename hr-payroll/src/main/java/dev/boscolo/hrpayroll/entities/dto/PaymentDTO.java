package dev.boscolo.hrpayroll.entities.dto;

import java.io.Serializable;

import dev.boscolo.hrpayroll.entities.Payment;

public class PaymentDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String name;
	private Double dailyIncome;
	private Integer days;
	
	public PaymentDTO() {
		
	}

	public PaymentDTO(String name, Double dailyIncome, Integer days) {
		this.name = name;
		this.dailyIncome = dailyIncome;
		this.days = days;
	}
	
	public PaymentDTO(Payment payment) {
		this.name = payment.getName();
		this.dailyIncome = payment.getDailyIncome();
		this.days = payment.getDays();
	}	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getDailyIncome() {
		return dailyIncome;
	}

	public void setDailyIncome(Double dailyIncome) {
		this.dailyIncome = dailyIncome;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}
	
	public double getTotal() {
		return days*dailyIncome;
	}
	
}

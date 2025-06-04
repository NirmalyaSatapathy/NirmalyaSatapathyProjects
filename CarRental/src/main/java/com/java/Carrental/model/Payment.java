package com.java.Carrental.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Payment {
	private int paymentId;
	private int leaseId;
	private double amount;
	private LocalDate date;
}

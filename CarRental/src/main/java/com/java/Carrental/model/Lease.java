package com.java.Carrental.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Lease {
//	leaseId int  primary key,
//	customerId int,
//	carId int,
//	leaseType enum('DAILY','MONTHLY'),
//	duration int,
//	startDate date,
//	endDate date,
//	cost decimal,
//	isReturned boolean default false,
	private int leaseId;
	private int customerId;
	private int carId;
	private String type;
	private long duration;
	private double cost;
	private LocalDate startDate;
	private LocalDate endDate;
	private boolean isReturned;
	private double advance;
}

package com.java.Carrental.model;

import lombok.Data;

@Data
public class Car {
	private int id;
	private String model;
	private String brand;
	private boolean availability;
	private double perDay;
	private double perMonth;
	private String engine;
}

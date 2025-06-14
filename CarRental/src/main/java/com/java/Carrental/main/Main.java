package com.java.Carrental.main;


import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.java.Carrental.dao.CarImp;
import com.java.Carrental.dao.Cardao;
import com.java.Carrental.dao.LeaseImp;
import com.java.Carrental.dao.Leasedao;
import com.java.Carrental.dao.Paymentdao;
import com.java.Carrental.dao.PaymentsaoImp;
import com.java.Carrental.model.Car;
import com.java.Carrental.model.Lease;
import com.java.Carrental.model.Payment;

public class Main {
    public static void main(String[] args) {
        Cardao carDao = new CarImp();
        Leasedao leaseDao = new LeaseImp();
        Paymentdao paymentDao = new PaymentsaoImp();

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n====== Car Rental Management System ======");
            System.out.println("1. Add New Car");
            System.out.println("2. View Available Cars");
            System.out.println("3. Rent a Car (Lease)");
            System.out.println("4. View All Leases");
            System.out.println("5. Make Payment");
            System.out.println("6. View All Payments");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    scanner.nextLine(); // consume leftover newline
                    Car newCar = new Car();
                    System.out.print("Enter brand: ");
                    newCar.setBrand(scanner.nextLine());
                    System.out.print("Enter model: ");
                    newCar.setModel(scanner.nextLine());
                   System.out.println("Enter car id");
                   newCar.setId(scanner.nextInt());
                   newCar.setAvailability(true);
				try {
					carDao.addCar(newCar);
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                    break;

                case 2:
				List<Car> availableCars;
				try {
					availableCars = carDao.showAvailableCars();
					 System.out.println("\nAvailable Cars:");
	                    for (Car car : availableCars) {
	                        System.out.println(car);
	                    }
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                   
                    break;

                case 3:
                	Lease l=new Lease();
                    System.out.print("Enter Car ID to lease: ");
                    l.setCarId(scanner.nextInt());
                    scanner.nextLine(); // consume newline
                    System.out.print("Enter customer id ");
                    l.setCustomerId(scanner.nextInt());
                    System.out.print("Enter lease start date (YYYY-MM-DD): ");
                    l.setStartDate(LocalDate.parse(scanner.next()));
                    System.out.print("Enter lease end date (YYYY-MM-DD): ");
                    l.setEndDate(LocalDate.parse(scanner.next()));;
				try {
					new LeaseImp().createLease(l);
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                    break;

                case 4:
				List<Lease> leases;
				try {
					leases = new LeaseImp().showLeaseHistory();
					 for (Lease lease : leases) {
	                        System.out.println(lease);
	                    }
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                    System.out.println("\nAll Leases:");
                   
                    break;

                case 5:
                	Payment p=new Payment();
                	System.out.println("Enter payment id");
                	p.setPaymentId(scanner.nextInt());
                    System.out.print("Enter Lease ID for payment: ");
                    p.setLeaseId(scanner.nextInt());
                    System.out.println("Enter amount");
                    p.setAmount(scanner.nextDouble());
                    System.out.println("Enter date");
                    p.setDate(LocalDate.parse(scanner.next()));
				try {
					new PaymentsaoImp().makePayment(p);
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                    
                    break;

                case 6:
				List<Payment> payments;
				try {
					payments = paymentDao.getAllPayments();
					 for (Payment payment : payments) {
	                        System.out.println(payment);
	                    }
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                    System.out.println("\nAll Payments:");
                   
                    break;

                case 0:
                    System.out.println("Exiting system. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 0);

        scanner.close();
    }
}


package com.java.Carrental.main;



import com.java.Carrental.dao.PaymentsaoImp;
import com.java.Carrental.model.Payment;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class PaymentMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PaymentsaoImp paymentDao = new PaymentsaoImp();

        while (true) {
            System.out.println("\n--- Payment Menu ---");
            System.out.println("1. Make a Payment");
            System.out.println("2. View All Payments");
            System.out.println("3. View Payments by Lease ID");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            try {
                switch (choice) {
                    case 1:
                        Payment payment = new Payment();
                        System.out.print("Enter Lease ID: ");
                        payment.setLeaseId(scanner.nextInt());

                        System.out.print("Enter Payment ID: ");
                        payment.setPaymentId(scanner.nextInt());

                        System.out.print("Enter Amount: ");
                        payment.setAmount(scanner.nextDouble());

                        scanner.nextLine(); // consume newline
                        System.out.print("Enter Payment Date (YYYY-MM-DD): ");
                        payment.setDate(LocalDate.parse(scanner.nextLine()));

                        paymentDao.makePayment(payment);
                        System.out.println("Payment recorded successfully.");
                        break;

                    case 2:
                        List<Payment> allPayments = paymentDao.getAllPayments();
                        System.out.println("\nAll Payments:");
                        for (Payment p : allPayments) {
                            System.out.println(p);
                        }
                        break;

                    case 3:
                        System.out.print("Enter Lease ID to filter payments: ");
                        int leaseId = scanner.nextInt();
                        List<Payment> filteredPayments = paymentDao.getPaymentsByLeaseId(leaseId);
                        System.out.println("\nPayments for Lease ID " + leaseId + ":");
                        for (Payment p : filteredPayments) {
                            System.out.println(p);
                        }
                        break;

                    case 4:
                        System.out.println("Exiting...");
                        scanner.close();
                        System.exit(0);

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}


package com.java.Carrental.main;



import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.java.Carrental.dao.LeaseImp;
import com.java.Carrental.dao.Leasedao;
import com.java.Carrental.model.Lease;

public class LeaseMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Leasedao dao = new LeaseImp();

        while (true) {
            System.out.println("\n====== Lease Management Menu ======");
            System.out.println("1. Create Lease");
            System.out.println("2. Return Car");
            System.out.println("3. Show Active Leases");
            System.out.println("4. Show Lease History");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            try {
                switch (choice) {
                    case 1:
                        Lease lease = new Lease();
                        System.out.print("Enter Lease ID: ");
                        lease.setLeaseId(sc.nextInt());
                        System.out.print("Enter Customer ID: ");
                        lease.setCustomerId(sc.nextInt());
                        System.out.print("Enter Car ID: ");
                        lease.setCarId(sc.nextInt());
                        sc.nextLine(); // consume newline
                        System.out.print("Enter Lease Type: ");
                        lease.setType(sc.nextLine());
                        System.out.print("Enter Duration (days): ");
                        lease.setDuration(sc.nextInt());
                        sc.nextLine();
                        System.out.print("Enter Start Date (YYYY-MM-DD): ");
                        lease.setStartDate(LocalDate.parse(sc.nextLine()));
                        System.out.print("Enter End Date (YYYY-MM-DD): ");
                        lease.setEndDate(LocalDate.parse(sc.nextLine()));
                        System.out.println("enter cost");
                        lease.setCost(sc.nextInt());
                        lease.setReturned(false);
                        dao.createLease(lease);
                        System.out.println("Lease created successfully.");
                        break;

                    case 2:
                        System.out.print("Enter Lease ID to return car: ");
                        int leaseId = sc.nextInt();
                        dao.returnCar(leaseId);
                        System.out.println("Car returned and status updated.");
                        break;

                    case 3:
                        List<Lease> activeLeases = dao.showActiveLease();
                        for (Lease l : activeLeases) {
                            System.out.println("\n--- Active Lease ---");
                            System.out.println("Lease ID: " + l.getLeaseId());
                            System.out.println("Customer ID: " + l.getCustomerId());
                            System.out.println("Car ID: " + l.getCarId());
                            System.out.println("Type: " + l.getType());
                            System.out.println("Duration: " + l.getDuration());
                            System.out.println("Start: " + l.getStartDate());
                            System.out.println("End: " + l.getEndDate());
                            System.out.println("Returned: " + l.isReturned());
                            System.out.println("cost:"+l.getCost());
                        }
                        break;

                    case 4:
                        List<Lease> allLeases = dao.showLeaseHistory();
                        for (Lease l : allLeases) {
                            System.out.println("\n--- Lease History ---");
                            System.out.println("Lease ID: " + l.getLeaseId());
                            System.out.println("Customer ID: " + l.getCustomerId());
                            System.out.println("Car ID: " + l.getCarId());
                            System.out.println("Type: " + l.getType());
                            System.out.println("Duration: " + l.getDuration());
                            System.out.println("Start: " + l.getStartDate());
                            System.out.println("End: " + l.getEndDate());
                            System.out.println("Returned: " + l.isReturned());
                        }
                        break;

                    case 5:
                        System.out.println("Exiting Lease Management...");
                        sc.close();
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}


package com.java.Carrental.main;


import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.java.Carrental.dao.CustomerDao;
import com.java.Carrental.dao.CustomerImp;
import com.java.Carrental.model.Customer;

public class CustomerMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CustomerDao customerDao = new CustomerImp();

        while (true) {
            System.out.println("\n===== Customer Management =====");
            System.out.println("1. Add Customer");
            System.out.println("2. Update Customer");
            System.out.println("3. Get Customer by ID");
            System.out.println("4. Get All Customers");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            try {
                switch (choice) {
                    case 1:
                        Customer newCustomer = new Customer();
                        System.out.print("Enter Customer ID: ");
                        newCustomer.setId(sc.nextInt());
                        sc.nextLine();
                        System.out.print("Enter Name: ");
                        newCustomer.setName(sc.nextLine());
                        System.out.print("Enter Email: ");
                        newCustomer.setEmail(sc.nextLine());
                        System.out.print("Enter Phone: ");
                        newCustomer.setPhone(sc.nextLine());
                        customerDao.addCustomer(newCustomer);
                        System.out.println("Customer added successfully!");
                        break;

                    case 2:
                        Customer updateCustomer = new Customer();
                        System.out.print("Enter Customer ID to update: ");
                        updateCustomer.setId(sc.nextInt());
                        sc.nextLine();
                        System.out.print("Enter New Name: ");
                        updateCustomer.setName(sc.nextLine());
                        System.out.print("Enter New Email: ");
                        updateCustomer.setEmail(sc.nextLine());
                        System.out.print("Enter New Phone: ");
                        updateCustomer.setPhone(sc.nextLine());
                        customerDao.updateCustomer(updateCustomer);
                        System.out.println("Customer updated successfully!");
                        break;

                    case 3:
                        System.out.print("Enter Customer ID: ");
                        int id = sc.nextInt();
                        Customer cus = customerDao.getCustomerById(id);
                        if (cus != null && cus.getId() != 0) {
                            System.out.println("ID: " + cus.getId());
                            System.out.println("Name: " + cus.getName());
                            System.out.println("Email: " + cus.getEmail());
                            System.out.println("Phone: " + cus.getPhone());
                        } else {
                            System.out.println("Customer not found.");
                        }
                        break;

                    case 4:
                        List<Customer> list = customerDao.getAllCustomer();
                        for (Customer c : list) {
                            System.out.println("----------------------");
                            System.out.println("ID: " + c.getId());
                            System.out.println("Name: " + c.getName());
                            System.out.println("Email: " + c.getEmail());
                            System.out.println("Phone: " + c.getPhone());
                        }
                        break;

                    case 5:
                        System.out.println("Exiting...");
                        sc.close();
                        System.exit(0);

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
    }
}


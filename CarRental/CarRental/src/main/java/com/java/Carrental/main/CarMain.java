package com.java.Carrental.main;

import com.java.Carrental.dao.CarImp;
import com.java.Carrental.model.Car;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class CarMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CarImp carDao = new CarImp();

        while (true) {
            System.out.println("\n--- Car Rental Menu ---");
            System.out.println("1. Add Car");
            System.out.println("2. Remove Car");
            System.out.println("3. Show Available Cars");
            System.out.println("4. Show Rented Cars");
            System.out.println("5. Find Car by ID");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            try {
                switch (choice) {
                    case 1:
                        Car newCar = new Car();
                        System.out.print("Enter Car ID: ");
                        newCar.setId(scanner.nextInt());
                        scanner.nextLine(); // consume newline
                        System.out.print("Enter Car Brand: ");
                        newCar.setBrand(scanner.nextLine());
                        System.out.print("Enter Car Model: ");
                        newCar.setModel(scanner.nextLine());
                        System.out.print("Is Car Available (true/false): ");
                        newCar.setAvailability(scanner.nextBoolean());
                        carDao.addCar(newCar);
                        break;

                    case 2:
                        System.out.print("Enter Car ID to remove: ");
                        int removeId = scanner.nextInt();
                        carDao.remove(removeId);
                        break;

                    case 3:
                        List<Car> availableCars = carDao.showAvailableCars();
                        System.out.println("Available Cars:");
                        for (Car car : availableCars) {
                            System.out.println(car);
                        }
                        break;

                    case 4:
                        List<Car> rentedCars = carDao.showRentedCars();
                        System.out.println("Rented Cars:");
                        for (Car car : rentedCars) {
                            System.out.println(car);
                        }
                        break;

                    case 5:
                        System.out.print("Enter Car ID to find: ");
                        int findId = scanner.nextInt();
                        Car foundCar = carDao.findCarById(findId);
                        if (foundCar.getId() != 0) {
                            System.out.println("Car found: " + foundCar);
                        } else {
                            System.out.println("Car not found.");
                        }
                        break;

                    case 6:
                        System.out.println("Exiting...");
                        scanner.close();
                        System.exit(0);

                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}

package com.java.lms.main;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.jdbc.lms.LeaveStatus;
import com.jdbc.lms.LeaveType;
import com.jdbc.lms.Leave;
import com.jdbc.lms.bal.LeaveBal;


public class Main {
    static LeaveBal leaveBal;
    static Leave leave;
    static Scanner scanner;

    static {
        leaveBal = new LeaveBal();
        scanner=new Scanner(System.in);
    }

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\nLeave Management System Options:");
            System.out.println("Enter 1 to apply for leave");
            System.out.println("Enter 2 to see all the leavedetails");
            System.out.println("Enter 3 to see pending leaves(Manager id required)");
            System.out.println("Enter 4 for approval(Manager id is required)");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            try {
                switch (choice) {
                    case 1:
                        applyLeave();
                        break;
                    case 2:
                        viewAllLeave();
                        break;
                    case 3:
                        showPendingLeave();
                        break;
                    case 4:
                    	changeLeaveStatus();
                    case 0:
                        System.out.println("Exiting Leave Management System. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        } while (choice != 0);

        scanner.close();
    }

    private static void changeLeaveStatus() throws ClassNotFoundException, SQLException {
    	System.out.println("enter manager id");
    	int managerId=scanner.nextInt();
    	System.out.println("enter leave id");
    	int leaveid=scanner.nextInt();
    	System.out.println("Give approval");
    	scanner.nextLine();
    	LeaveStatus status=LeaveStatus.valueOf(scanner.next().toUpperCase());
    	System.out.println("enter comments");
    	String comments=scanner.next();
		System.out.println(leaveBal.changeLeaveStatusDaoBal(managerId,leaveid,status,comments));
		
	}

	public static void applyLeave() throws ClassNotFoundException, SQLException {
        leave = new Leave();

        System.out.println("Enter Employee ID: ");
        leave.setEmpid(scanner.nextInt());
        scanner.nextLine();

        System.out.println("Enter Start Date (yyyy-mm-dd): ");
        leave.setStartdate(Date.valueOf(scanner.nextLine()));

        System.out.println("Enter End Date (yyyy-mm-dd): ");
        leave.setEnddate(Date.valueOf(scanner.nextLine()));

        System.out.println("Enter Leave Reason: ");
        leave.setLeavereason(scanner.nextLine());

        System.out.println("Enter Leave Type (EL/SL/ML): ");
        String type = scanner.next().toUpperCase();
        leave.setLeavetype(LeaveType.valueOf(type));

        leave.setLeavestatus(LeaveStatus.PENDING);

        String result = leaveBal.applyALeaveBal(leave);
        System.out.println("Leave Application Result: " + result);
    }

    public static void viewAllLeave() throws ClassNotFoundException, SQLException {
        System.out.print("Enter Employee ID to view leave history: ");
        int empId = scanner.nextInt();
        scanner.nextLine();

        List<Leave> leaveHistory = leaveBal.allLeaveTakenByEmpBal(empId);

        if (leaveHistory.isEmpty()) {
            System.out.println("Leave history not found for employ id " + empId);
        } else {
            System.out.println("\nLeave History for employ id " + empId);
            for (Leave leave : leaveHistory) {
                System.out.println("Leave ID: " + leave.getLeaveid() +
                					", Employee id is: " + leave.getEmpid() +
                                   " Leave Start Date: " + leave.getStartdate() +
                                   " Leave End Date: " + leave.getEnddate() +
                                   " Reason for the leave: " + leave.getLeavereason() +
                                   " leave type for the employ:" + leave.getLeavetype() +
                                   " status of your request is:" + leave.getLeavestatus());
            }
        }
    }

    public static void showPendingLeave() throws ClassNotFoundException, SQLException {
        System.out.print("Enter your Manager ID to view pending leave requests: ");
        int managerId = scanner.nextInt();
        scanner.nextLine();

        List<Leave> pendingLeaves = leaveBal.showPendingLeaves(managerId);

        if (pendingLeaves.isEmpty()) {
            System.out.println("No pending leave requests found for Manager ID: " + managerId);
        } else {
            System.out.println("\nPending Leave Requests for Manager ID: " + managerId);
            for (Leave leave : pendingLeaves) {
                System.out.println("Leave ID: " + leave.getLeaveid() +
                                   ", Employee ID: " + leave.getEmpid() +
                                   ", Start Date: " + leave.getStartdate() +
                                   ", End Date: " + leave.getEnddate() +
                                   ", Reason: " + leave.getLeavereason() +
                                   ", Type: " + leave.getLeavetype() +
                                   ", Status: " + leave.getLeavestatus());
            }
        }
    }
}
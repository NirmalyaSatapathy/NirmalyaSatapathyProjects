package com.java.jsf;

import java.util.List;

public class Dummy {
    public static void main(String[] args) {
        try {
            UserDaoImpl dao = new UserDaoImpl();
            List<DailyExpenses> expenses = dao.showAllExpenses(1);
            
            for (DailyExpenses exp : expenses) {
                System.out.println("Expense ID: " + exp.getExpId());
                System.out.println("Group ID: " + exp.getGroupId());
                System.out.println("Date: " + exp.getExpenseDate());
                System.out.println("Description: " + exp.getExpenseDescription());
                System.out.println("Paid By: " + exp.getPaidBy());
                System.out.println("Amount: " + exp.getAmount());
                System.out.println("-------------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace(); // To catch errors like DB connection issues
        }
    }
}

package com.java.jsf;

import java.util.ArrayList;
import java.util.List;

public class ExpenseController {

	private UserDaoImpl userDaoImpl = new UserDaoImpl();
	private int groupId;
	private List<DailyExpenses> dailyExpensesList = new ArrayList<DailyExpenses>();

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public UserDaoImpl getUserDaoImpl() {
		return userDaoImpl;
	}

	public void setUserDaoImpl(UserDaoImpl userDaoImpl) {
		this.userDaoImpl = userDaoImpl;
	}

	public List<DailyExpenses> getDailyExpensesList() {
		return dailyExpensesList;
	}

	public void setDailyExpensesList(List<DailyExpenses> dailyExpensesList) {
		this.dailyExpensesList = dailyExpensesList;
	}

	public String dailyExpensesListMethod() {
		dailyExpensesList = userDaoImpl.showAllExpenses(this.groupId);
		return null;
	}
}

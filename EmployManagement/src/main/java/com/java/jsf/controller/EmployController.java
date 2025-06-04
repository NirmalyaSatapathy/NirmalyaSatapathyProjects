package com.java.jsf.controller;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;

import com.java.jsf.dao.EmployDao;
import com.java.jsf.model.Employ;
import com.java.jsf.model.Login;

public class EmployController {

	private EmployDao employDao;
	private Employ employ;

	private String sortField;
	private boolean ascending = true;
	private List<Employ> employList;

	private int first = 0;
	private int pageSize = 2; // Number of records per page

	// ---------- Pagination Controls ----------
	public void next() {
		if (first + pageSize < getFullList().size()) {
			first += pageSize;
		}
	}

	public void previous() {
		if (first - pageSize >= 0) {
			first -= pageSize;
		}
	}

	public boolean isNextAvailable() {
		return first + pageSize < getFullList().size();
	}

	public boolean isPreviousAvailable() {
		return first > 0;
	}
	private List<Employ> getFullList() {
		if (employList == null) {
			employList = employDao.showEmployDao();
			sortList();
		}
		return employList;
	}

	// ---------- Sorting ----------
	public void sortBy(String field) {
		if (field.equals(sortField)) {
			ascending = !ascending; // toggle sort direction
		} else {
			sortField = field;
			ascending = true;
		}
		first = 0; // reset to first page when sorting
		sortList();
	}

	private void sortList() {
		if (sortField == null || employList == null)
			return;

		Collections.sort(employList, (e1, e2) -> {
			try {
				Field f = Employ.class.getDeclaredField(sortField);
				f.setAccessible(true);
				Comparable v1 = (Comparable) f.get(e1);
				Comparable v2 = (Comparable) f.get(e2);
				return ascending ? v1.compareTo(v2) : v2.compareTo(v1);
			} catch (Exception ex) {
				return 0;
			}
		});
	}

	// ---------- DAO and View Logic ----------
	public EmployDao getEmployDao() {
		return employDao;
	}

	public void setEmployDao(EmployDao employDao) {
		this.employDao = employDao;
	}

	public Employ getEmploy() {
		return employ;
	}

	public void setEmploy(Employ employ) {
		this.employ = employ;
	}

	public String validate(Login login) {
		return employDao.validate(login);
	}

	public List<Employ> showEmploy() {
		List<Employ> all = getFullList();
		int toIndex = Math.min(first + pageSize, all.size());
		return all.subList(first, toIndex);
	}

	public String searchEmploy(int empno) {
		return employDao.searchEmployDao(empno);
	}

	public String addEmploy(Employ employ) {
		return employDao.addEmploy(employ);
	}

	public List<Employ> showEmployListByDept(String dept) {
		return employDao.getEmployListByDept(dept);
	}
}

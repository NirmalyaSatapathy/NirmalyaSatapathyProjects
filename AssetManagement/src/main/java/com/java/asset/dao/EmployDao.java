package com.java.asset.dao;

import java.sql.SQLException;
import java.util.List;

import com.java.asset.model.Employ;

public interface EmployDao {
public String addEmployDao(Employ employ) throws ClassNotFoundException, SQLException;
public List<Employ> showEmployDao() throws ClassNotFoundException, SQLException;
}

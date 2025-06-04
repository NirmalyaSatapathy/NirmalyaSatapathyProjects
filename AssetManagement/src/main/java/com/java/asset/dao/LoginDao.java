package com.java.asset.dao;

import com.java.asset.model.Login;

public interface LoginDao {
public String adminLogin(Login login);
public String userLogin(Login login);
}

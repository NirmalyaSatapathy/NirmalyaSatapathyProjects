package com.java.asset.controller;

import org.apache.log4j.Logger;

import com.java.asset.bal.LoginBal;
import com.java.asset.model.Login;

public class LoginController {
LoginBal loginBal;
private static final Logger log = Logger.getLogger("com.java.asset.Controller.EmployController");
public LoginBal getLoginBal() {
	return loginBal;
}
public void setLoginBal(LoginBal loginBal) {
	this.loginBal = loginBal;
}
public String adminLoginController(Login login)
{
	log.info("in adminlogin controller");
	return loginBal.adminLogin(login);
}
public String userLoginController(Login login)
{
	log.info("in employ login controller");
	return loginBal.userLogin(login);
}
}

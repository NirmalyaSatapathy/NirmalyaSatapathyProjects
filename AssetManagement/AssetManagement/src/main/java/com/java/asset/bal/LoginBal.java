package com.java.asset.bal;

import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import com.java.asset.dao.LoginDao;
import com.java.asset.model.Login;

public class LoginBal {
	LoginDao loginDao;
	private static final Logger log = Logger.getLogger("com.java.asset.Controller.EmployController");
public LoginDao getLoginDao() {
		return loginDao;
	}
	public void setLoginDao(LoginDao loginDao) {
		this.loginDao = loginDao;
	}
public String adminLogin(Login login)
{
	log.info("in adminlogin bal");
	 FacesContext.getCurrentInstance().getExternalContext()
     .getSessionMap().put("adminName", login.getName());
	 loginDao.adminLogin(login);
	 return "AdminDashboard?faces-redirect=true";
}
public String userLogin(Login login)
{
	 loginDao.userLogin(login);
	 return "EmployDashBoard?faces-redirect=true";
}
}

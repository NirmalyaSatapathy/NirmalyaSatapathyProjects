package com.java.asset.model;

import java.util.Date;

public class ReturnAsset {
private String name;
private String email;
private String asset;
private Date returnDate;
public Date getReturnDate() {
	return returnDate;
}
public void setReturnDate(Date returnDate) {
	this.returnDate = returnDate;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getAsset() {
	return asset;
}
public void setAsset(String asset) {
	this.asset = asset;
}

}

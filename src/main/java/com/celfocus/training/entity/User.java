package com.celfocus.training.entity;

import java.util.Date;

public class User {

	private String username;
	private Date birthday;
	private boolean isLegalAge;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public boolean isLegalAge() {
		return isLegalAge;
	}

	public void setLegalAge(boolean legalAge) {
		isLegalAge = legalAge;
	}
}

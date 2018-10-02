package com.shj.onlineshopping.model;

import java.io.Serializable;

import com.shj.shoppingbackend.dto.Address;
import com.shj.shoppingbackend.dto.User;

public class RegisterModel implements Serializable{

	/**
	 * Version ID for serialization
	 */
	private static final long serialVersionUID = 1L;
	
	private User user;
	private Address billing;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Address getBilling() {
		return billing;
	}
	public void setBilling(Address billing) {
		this.billing = billing;
	}	
	
}

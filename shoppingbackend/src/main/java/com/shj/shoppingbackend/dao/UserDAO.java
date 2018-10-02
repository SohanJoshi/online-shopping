package com.shj.shoppingbackend.dao;

import java.util.List;

import com.shj.shoppingbackend.dto.Address;
import com.shj.shoppingbackend.dto.Cart;
import com.shj.shoppingbackend.dto.User;

public interface UserDAO {

	// Add an user
	boolean addUser(User user);
	User getUserByEmail(String email);
	
	// Update a cart
	boolean updateCart(Cart cart);
	
	// Add an address
	boolean addAddress(Address address);
	
	// Alternative methods 
	
/*	Address getBillingAddress(int userId);
	List<Address> listShippingAddresses(int userId);
*/
	Address getBillingAddress(User user);
	List<Address> listShippingAddresses(User user);
}

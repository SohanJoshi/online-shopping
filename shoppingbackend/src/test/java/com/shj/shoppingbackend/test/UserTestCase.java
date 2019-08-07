package com.shj.shoppingbackend.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.shj.shoppingbackend.dao.UserDAO;
import com.shj.shoppingbackend.dto.Address;
import com.shj.shoppingbackend.dto.User;

public class UserTestCase {

	private AnnotationConfigApplicationContext context;
	private static UserDAO userDAO;
	private User user = null;
//	private Cart cart = null;
	private Address address = null;
	
	@Before
	public void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.shj.shoppingbackend");
		context.refresh();
		
		userDAO = context.getBean(UserDAO.class);
	}
	
	/*@Test
	public void testAdd() {
		
		user = new User();
		user.setFirstName("Hritik");
		user.setLastName("Roshan");
		user.setRole("USER");
		user.setContactNumber("9211-420");
		user.setPassword("HRSUcks");
		user.setEmail("hr@gmail.com");
		
		// Adding the user
		
		assertTrue("Failed to add user", userDAO.addUser(user));
		
		address = new Address();
		address.setAddressLineOne("39 MG Road");
		address.setAddressLineTwo("Some Porsche Area");
		address.setCity("Mumbai");
		address.setState("Maharashtra");
		address.setCountry("India");
		address.setPostalCode("400043");
		address.setBilling(true);
		
		// Linking the address to the user
		
		address.setUserId(user.getId());
		
		assertTrue("Failed to add address", userDAO.addAddress(address));
		
		if(user.getRole().equalsIgnoreCase("USER")) {
			
			// Creating a new cart for the user
			
			cart = new Cart();
			cart.setUser(user);

			// Adding the cart
			
			assertTrue("Failed to add cart", userDAO.addCart(cart));
			
			// Creating a new shipping address
			
			address = new Address();
			address.setAddressLineOne("39 MG Road");
			address.setAddressLineTwo("Some Porsche Area");
			address.setCity("Mumbai");
			address.setState("Maharashtra");
			address.setCountry("India");
			address.setPostalCode("400043");
			address.setShipping(true);
			
			// Linking the shipping address to the user
			
			address.setUserId(user.getId());
			
			// Adding the shipping address
			
			assertTrue("Failed to add shipping address", userDAO.addAddress(address));
			
			
		}
	}*/
	
	/*@Test
	public void testAdd() {
		
		user = new User();
		user.setFirstName("Hritik");
		user.setLastName("Roshan");
		user.setRole("USER");
		user.setContactNumber("9211-420");
		user.setPassword("HRSUcks");
		user.setEmail("hr@gmail.com");
		
		if(user.getRole().equalsIgnoreCase("USER")) {
			
			// Creating a new cart for the user
			
			cart = new Cart();
			cart.setUser(user);
			
			// Attaching the cart to the address
			
			user.setCart(cart);
		}
		
		// Adding the user
		
		assertTrue("Failed to add user", userDAO.addUser(user));
				
	}*/

	/*@Test
	public void testUpdateCart() {
		
		// Fetching the user by email
		user = userDAO.getEmailByUser("hr@gmail.com");
		
		cart = user.getCart();
		
		cart.setCartLines(10);
		cart.setGrandTotal(5000);
		
		assertTrue("Failed to update cart", userDAO.updateCart(cart));
	}*/
	
	@Test
	public void testAddAddress() {
		
		// Add a user
		
		user = new User();
		user.setFirstName("Hritik");
		user.setLastName("Roshan");
		user.setRole("USER");
		user.setContactNumber("9211-420");
		user.setPassword("HRSUcks");
		user.setEmail("hr@gmail.com");
		
		assertTrue("Failed to add user", userDAO.addUser(user));
		
		// Add address for billing
	
		address = new Address();
		address.setAddressLineOne("39 MG Road");
		address.setAddressLineTwo("Some Porsche Area");
		address.setCity("Mumbai");
		address.setState("Maharashtra");
		address.setCountry("India");
		address.setPostalCode("400043");
		address.setBilling(true);
		
		// Attaching the address to the user
		
		address.setUser(user);
		
		// Adding the address
		
		assertTrue("Failed to add the address!", userDAO.addAddress(address));
		
		// Add shipping addresses
		
		address = new Address();
		address.setAddressLineOne("39 MG Road");
		address.setAddressLineTwo("Some Porsche Area");
		address.setCity("Mumbai");
		address.setState("Maharashtra");
		address.setCountry("India");
		address.setPostalCode("400043");
		address.setShipping(true);
		
		// Attaching the address to the user
		
		address.setUser(user);
		
		// Adding the address
		
		assertTrue("Failed to add the address!", userDAO.addAddress(address));
		
		
	}
	
/*	@Test
	public void testAddMultipleAddresses() {
		
		user = userDAO.getEmailByUser("hr@gmail.com");
		
		address = new Address();
		address.setAddressLineOne("39 MG Road");
		address.setAddressLineTwo("Some Porsche Area");
		address.setCity("Bangalore");
		address.setState("Karnataka");
		address.setCountry("India");
		address.setPostalCode("560037");
		address.setShipping(true);
		
		// Attaching the address to the user
 		
		address.setUser(user);
		
		// Adding the address
		
		assertTrue("Failed to add the address!", userDAO.addAddress(address));
		
	}*/
	
	@Test
	public void testGetAddresses() {
		user = userDAO.getUserByEmail("hr@gmail.com");
		
		assertEquals("Failed to fetch of the expected number of the shipping addresses", 
				2, userDAO.listShippingAddresses(user).size());
		
		assertEquals("Failed to fetch the billing address for the user!",
				"400043",userDAO.getBillingAddress(user).getPostalCode());
	}
}

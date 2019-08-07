package com.shj.onlineshopping.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.shj.onlineshopping.model.RegisterModel;
import com.shj.shoppingbackend.dao.UserDAO;
import com.shj.shoppingbackend.dto.Address;
import com.shj.shoppingbackend.dto.Cart;
import com.shj.shoppingbackend.dto.User;

@Component
public class RegisterHandler {

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	private static final String VALIDATION_SUCCESS = "success";
	private static final String VALIDATION_FAILURE = "failure";

	public RegisterModel init() {
		return new RegisterModel();
	}

	public void addUser(RegisterModel registerModel, User user) {
		registerModel.setUser(user);
	}
	
	public void addBilling(RegisterModel registerModel, Address billing) {
		registerModel.setBilling(billing);
	}
	
	public String saveAll(RegisterModel registerModel) {
		String transitionValue = "success";
		
		// Fetching the user
		
		User user = registerModel.getUser();
		
		if("USER".equalsIgnoreCase(user.getRole())) {
			Cart cart = new Cart();
			cart.setUser(user);
			user.setCart(cart);

		}
			
		// Encoding password in user
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		// Saving the User
		userDAO.addUser(user);
		
		// Get the Address
		
		Address billing = registerModel.getBilling();
		
		billing.setUser(user);
		billing.setBilling(true);
		
		userDAO.addAddress(billing);
		
		return transitionValue ;
	}
	
	public String validateUser(User user, MessageContext error ) {
		
		if(userDAO.getUserByEmail(user.getEmail()) != null) {
			error.addMessage(new MessageBuilder()
					.error()
					.source("email")
					.defaultText("The email is already in use as a User name")
						.build());
			return VALIDATION_FAILURE;
		}
		
		if(user.getConfirmPassword() == null ||
				!user.getConfirmPassword().equals(user.getPassword())) {
			error.addMessage(new MessageBuilder()
					.error()
					.source("confirmPassword")
					.defaultText("Password and Confirm Password do not match")
						.build());
			return VALIDATION_FAILURE;
		}
		
		return VALIDATION_SUCCESS;
	}
}

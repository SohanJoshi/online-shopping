package com.shj.onlineshopping.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.shj.onlineshopping.model.UserModel;
import com.shj.shoppingbackend.dao.UserDAO;
import com.shj.shoppingbackend.dto.User;

@ControllerAdvice
public class GlobalController {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private UserDAO userDAO;
	
	private UserModel userModel = null;
	
	@ModelAttribute("userModel")
	private UserModel getUesrModel() {
		
		if(session.getAttribute("userModel") == null) {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			
			User user = userDAO.getUserByEmail(authentication.getName());
			
			if(user != null) {
				
				// Create a new UserModel object to pass the user details.
				
				userModel = new UserModel();
				
				userModel.setId(user.getId());
				userModel.setEmail(user.getEmail());
				userModel.setRole(user.getRole());
				
				userModel.setFullName(user.getFirstName() + " " + user.getLastName());
				
				if("USER".equals(user.getRole())) {
					// Add Cart only if the user is a customer
					
					userModel.setCart(user.getCart());
				}
				
				session.setAttribute("userModel", userModel);
			}
			
			return userModel;
		}
		
		return (UserModel) session.getAttribute("userModel");
	}
	
}

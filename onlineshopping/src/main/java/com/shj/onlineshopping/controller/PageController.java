package com.shj.onlineshopping.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.shj.onlineshopping.exception.ProductNotFoundException;
import com.shj.shoppingbackend.dao.CategoryDAO;
import com.shj.shoppingbackend.dao.ProductDAO;
import com.shj.shoppingbackend.dto.Category;
import com.shj.shoppingbackend.dto.Product;

@Controller
@RequestMapping("/")
public class PageController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PageController.class);
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private ProductDAO productDAO; 
	
	@RequestMapping(value= {"/","/home","/index"})
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title","Home");
		
		LOGGER.info("Inside Page Controller Logger - INFO");
		LOGGER.debug("Inside Page Controller Logger - DEBUG");
		
		// passing the list of categories 
		mv.addObject("categories", categoryDAO.list());
		
		mv.addObject("userClickHome", true);
		return mv;
	}

	@RequestMapping(value= {"/about"})
	public ModelAndView about() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title","About Us");
		mv.addObject("userClickAbout", true);
		return mv;
	}
	
	@RequestMapping(value= {"/contact"})
	public ModelAndView contact() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title","Contact Us");
		mv.addObject("userClickContact", true);
		return mv;
	}
	
	/*
	 * Method to load all the products and based on category
	 * */
	@RequestMapping(value= {"/show/all/products"})
	public ModelAndView showAllProducts() {
		ModelAndView mv = new ModelAndView("page");
		
		mv.addObject("title","All Products");
		
		// passing the list of categories
		mv.addObject("categories",categoryDAO.list());
		
		mv.addObject("userClickAllProducts",true);
		
		return mv;
	}
	
	@RequestMapping(value= {"/show/category/{id}/products"})
	public ModelAndView showCategoryProducts(@PathVariable("id") int id) {
		ModelAndView mv = new ModelAndView("page");
		
		// category DAO to fetch a single category
		
		Category category = categoryDAO.get(id);
		
		mv.addObject("title",category.getName());
		
		// passing the list of categories
		mv.addObject("categories",categoryDAO.list());
		
		// passing the single category
		mv.addObject("category", category);
		
		mv.addObject("userClickCategoryProducts",true);
		
		return mv;
	}

	/**
	 * Viewing a Single Product	
	 * @throws ProductNotFoundException when there is no such product with the given product ID
	 */
	@RequestMapping(value="/show/{productId}/product")
	public ModelAndView showProduct(@PathVariable("productId") int productId) throws ProductNotFoundException {
		ModelAndView mv = new ModelAndView("page");
		
		Product product = productDAO.get(productId);
		
		if(product == null)
			throw new ProductNotFoundException();
		
		// Updating the product view count
		product.setViews(product.getViews() + 1);
		productDAO.update(product);
		// -----------------
		
		mv.addObject("title", product.getName());
		mv.addObject("product", product);
		mv.addObject("userClickShowProduct", true);
		
		return mv;
	}
	
	@RequestMapping(value= {"/login"})
	public ModelAndView login(@RequestParam(name="error", required=false)String error,
			@RequestParam(name="logout", required=false)String logout) {
		ModelAndView mv = new ModelAndView("login");
		if(error != null) {
			mv.addObject("message", "Invalid Username or Password");
		}
		
		if(logout != null) {
			mv.addObject("logout", "User successfully logged out");
		}

		
		mv.addObject("title","Login");
		return mv;
	}
	
	@RequestMapping(value= {"/access-denied"})
	public ModelAndView accessDenied() {
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("title","403 - Access Denied");
		mv.addObject("errorTitle","Aha! Caught You.");
		mv.addObject("errorDescription","You are not authorized to view this page.");
		return mv;
	}
	
	@RequestMapping( value = {"/perform-logout"})
	public String performLogout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		
		return "redirect:/login?logout";
	}
	
}

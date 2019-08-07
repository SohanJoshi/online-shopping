package com.shj.onlineshopping.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.shj.onlineshopping.util.FileUploadUtility;
import com.shj.onlineshopping.validator.ProductValidator;
import com.shj.shoppingbackend.dao.CategoryDAO;
import com.shj.shoppingbackend.dao.ProductDAO;
import com.shj.shoppingbackend.dto.Category;
import com.shj.shoppingbackend.dto.Product;

@Controller
@RequestMapping("/manage")
public class ManagementController {

	@Autowired
	private CategoryDAO categoryDAO ;
	
	@Autowired
	private ProductDAO productDAO;
	
	private static final Logger logger = LoggerFactory.getLogger(ManagementController.class);
	
	@RequestMapping(value={"/products"},method=RequestMethod.GET)
	public ModelAndView showManagingProducts(@RequestParam(value="operation",required=false) String operation){
		ModelAndView mv = new ModelAndView("page");
		
		Product nProduct = new Product();
		
		// Setting some new fields for the new products
		
		nProduct.setSupplierId(1);
		nProduct.setActive(true);
		
		mv.addObject("userClickManageProducts",true);
		mv.addObject("title","Manage Products");
		
		mv.addObject("product",nProduct);
		
		if("product".equals(operation)){
			mv.addObject("message","Product Submitted Successfully");
		}
		else if("category".equals(operation)) {
			mv.addObject("message", "Category Submitted Successfully");
		}
		
		return mv;
	}
	
	@ModelAttribute("categories")
	public List<Category> getCatgories() {
		return categoryDAO.list();
	}
	
	@RequestMapping(value={"/products"},method=RequestMethod.POST)
	public String handleProductSubmission(@Valid @ModelAttribute("product")
										Product mProduct,
										BindingResult result,
										Model model,
										HttpServletRequest request){
		
		if(mProduct.getId() == 0 || !mProduct.getFile().getOriginalFilename().equals(""))
		
			new ProductValidator().validate(mProduct, result);
	
		// Check for errors
		
		if(result.hasErrors()){
			model.addAttribute("title","Manage Products");
			model.addAttribute("userClickManageProducts",true);
			model.addAttribute("message", "Product Validation Failed for product submission");
			
			return "page";
		}
		
		logger.info(mProduct.toString());
		
		if(mProduct.getId() == 0)
			productDAO.add(mProduct);
		else
			productDAO.update(mProduct);
		
		if(!mProduct.getFile().getOriginalFilename().equals("")){
			FileUploadUtility.uploadFile(request, mProduct.getFile(), mProduct.getCode());
		}
		
		return "redirect:/manage/products?operation=product";
	}

	@RequestMapping(value={"/products/{id}/activation"})
	@ResponseBody
	public String handleProductActivation(@PathVariable("id") int id) {
		Product product = productDAO.get(id);
		
		boolean isActive = product.isActive();
		
		product.setActive(!product.isActive());
		productDAO.update(product);
		
		return (isActive)? 
				"You have successfully deactivated the product with id : " + id:
					"You have successfully deactivated the product with the id : " + id;
	}
	
	@RequestMapping(value={"/{id}/product"},method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView showEditProducts(@PathVariable("id") int id){
		ModelAndView mv = new ModelAndView("page");
		
		Product nProduct = productDAO.get(id);
		
		mv.addObject("userClickManageProducts",true);
		mv.addObject("title","Manage Products");
		
		// setting the fetched product
		mv.addObject("product",nProduct);
		
		return mv;
	}
	
	@ModelAttribute("category")
	public Category getCategory() {
		return new Category();
	}
	
	// To handle Category submission
	@RequestMapping(value="category", method=RequestMethod.POST)
	public String handleCategorySubmission(@ModelAttribute Category mCategory) {
		categoryDAO.add(mCategory);
		
		return "redirect:/manage/products?operation=category";
	}
	
}

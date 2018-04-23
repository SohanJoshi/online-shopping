package com.shj.onlineshopping.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

	@ExceptionHandler(NoHandlerFoundException.class)
	public ModelAndView handleNoHandlerFoundExcetion(){
		
		ModelAndView mv = new ModelAndView("error");
		
		mv.addObject("errorTitle","This page is not constructed!");
		
		mv.addObject("errorDescription","The page you are looking for is not available right now !");
		
		mv.addObject("title","404 Error Page");
		
		return mv;
	}
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ModelAndView handleProductNotFoundException() {
		ModelAndView mv = new ModelAndView("error");
		
		mv.addObject("errorTitle","Product Not Available");
		
		mv.addObject("errorDescription", "The product you are looking for is right now not avaialable in our database. "
				+ "You will soon be able to see one on this page as soon as we add one.");
		
		mv.addObject("title","Product Unavailable");
		
		return mv;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handleGeneralExcetion(Exception ex){
		
		ModelAndView mv = new ModelAndView("error");
		
		mv.addObject("errorTitle","Contact Your Administration");
		
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		
		ex.printStackTrace(pw);
		
		mv.addObject("errorDescription",sw.toString());
		
		mv.addObject("title","Error");
		
		return mv;
	}
	
}

package com.shj.onlineshopping.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtility {

	private static final String ABS_PATH = 
			"C:\\Sohan\\Project\\online-shopping\\onlineshopping\\src\\main\\webapp\\assets\\images\\";
	private static String REAL_PATH = "";
	private static Logger logger = LoggerFactory.getLogger(FileUploadUtility.class);
	
	public static void uploadFile(HttpServletRequest request, MultipartFile file, String code) {
		
		// Retrieving the real path
		REAL_PATH = request.getSession().getServletContext().getRealPath("/assets/images/");
		
		logger.info("The real path is : " + REAL_PATH);
		
		// Creating the directories if not exists
				
		if(! new File(ABS_PATH).exists()){
			new File(ABS_PATH).mkdirs();
		}
		
		if(! new File(REAL_PATH).exists()){
			new File(REAL_PATH).mkdirs();
		}
		
		try {
			// Server Path
			file.transferTo(new File(REAL_PATH + code + ".jpg"));
			// Project Directory Upload
			file.transferTo(new File(ABS_PATH + code + ".jpg"));

		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
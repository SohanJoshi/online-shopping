package com.shj.shoppingbackend.dao;

import java.util.List;

import com.shj.shoppingbackend.dto.Product;

public interface ProductDAO {

	Product get(int productId);
	List<Product> list();
	boolean add(Product product);
	boolean update(Product product);
	boolean delete(Product product);
	
	// Business Methods
	List<Product> listActiveProduct();
	List<Product> listActiveProductByCategory(int categoryId);
	List<Product> getLatestActiveProduct(int count);
	
}

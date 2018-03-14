package com.shj.shoppingbackend.dao;

import java.util.List;

import com.shj.shoppingbackend.dto.Category;

public interface CategoryDAO {

	boolean add(Category category);
	List<Category> list();
	Category get(int id);
	boolean updateCategory(Category category);
	boolean deleteCategory(Category category);
}

package com.shj.shoppingbackend.dao;

import java.util.List;

import com.shj.shoppingbackend.dto.Category;

public interface CategoryDAO {

	List<Category> list();

	Category get(int id);
}

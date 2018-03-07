package com.shj.shoppingbackend.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.shj.shoppingbackend.dao.CategoryDAO;
import com.shj.shoppingbackend.dto.Category;

@Repository("categoryDAO")
public class CategoryDAOImpl implements CategoryDAO {
	private static List<Category> categories = new ArrayList<>();

	static {
		Category category = new Category();

		// first category
		category.setId(1);
		category.setName("Television");
		category.setDescription("This is some description for television");
		category.setImageURL("CAT_1.img");
		category.setActive(true);

		categories.add(category);

		// second category
		category = new Category();

		category.setId(2);
		category.setName("Mobile");
		category.setDescription("This is some description for mobile");
		category.setImageURL("CAT_2.img");
		category.setActive(true);

		categories.add(category);

		// thirf category
		category = new Category();

		category.setId(3);
		category.setName("Laptops");
		category.setDescription("This is some description for laptops");
		category.setImageURL("CAT_3.img");
		category.setActive(true);

		categories.add(category);
	}

	@Override
	public List<Category> list() {
		return categories;
	}

	@Override
	public Category get(int id) {
		
		for(Category category : categories)
			if(category.getId() == id)
				return category;
		return null;
	}

}

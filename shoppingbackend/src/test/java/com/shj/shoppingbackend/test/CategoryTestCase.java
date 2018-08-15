package com.shj.shoppingbackend.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.shj.shoppingbackend.dao.CategoryDAO;
import com.shj.shoppingbackend.dto.Category;

public class CategoryTestCase {

	private static AnnotationConfigApplicationContext context;
	private static CategoryDAO categoryDAO;
	
	@BeforeClass
	public static void init() {

		context = new AnnotationConfigApplicationContext();
		context.scan("com.shj.shoppingbackend");
		context.refresh();

		categoryDAO = context.getBean(CategoryDAO.class);

	}

	/*
	 * @Test public void testAddCategory() { Category category = new Category();
	 * 
	 * category.setName("Television");
	 * category.setDescription("This is some description for television");
	 * category.setImageURL("CAT_1.img"); category.setActive(true);
	 * 
	 * assertTrue("Something went wrong while adding a category inside the table",
	 * categoryDAO.add(category)); }
	 */

	/*
	 * @Test public void testGetCateggory() { category = categoryDAO.get(1);
	 * 
	 * assertEquals("Something went wrong while fetching a category using a id from the table",
	 * "Television", category.getName()); }
	 */

	/*
	 * @Test public void testUpdateCategory() { category = categoryDAO.get(4);
	 * 
	 * category.setName("TV");
	 * 
	 * assertTrue("Successfully updated category in the table",
	 * categoryDAO.updateCategory(category));
	 * assertEquals("Something went wrong while updating the data from table", "TV",
	 * categoryDAO.get(4).getName()); }
	 */

	/*
	 * @Test public void testDeleteCategory() { category = categoryDAO.get(4);
	 * 
	 * assertTrue("Successfully updated category in the table",
	 * categoryDAO.deleteCategory(category));
	 * assertEquals("Something went wrong while deleting data from table", false,
	 * categoryDAO.get(4).isActive()); }
	 */

	/*
	 * @Test public void testList() {
	 * assertEquals("Something went wrong while fetching active active categories from the table",3,
	 * categoryDAO.list().size()); }
	 */

	@Test
	public void testCRUDOperations() {
		
		//check for add operation
		
		Category category = new Category();

		category.setName("Laptops");
		category.setDescription("This is some description for laptops");
		category.setImageURL("CAT_1.img");
		category.setActive(true);

		assertTrue("Something went wrong while adding a category inside the table", categoryDAO.add(category));

		category = new Category();

		category.setName("Television");
		category.setDescription("This is some description for television");
		category.setImageURL("CAT_2.img");
		category.setActive(true);

		assertTrue("Something went wrong while adding a category inside the table", categoryDAO.add(category));

		
		// check for update operation
		
		category = categoryDAO.get(2);

		category.setName("TV");

		assertTrue("Something went wrong while updating category in the table", categoryDAO.updateCategory(category));
		assertEquals("Something went wrong while verifying the updated data from table", "TV", categoryDAO.get(2).getName());

		// check for delete operation
		
		category = categoryDAO.get(2);

		assertTrue("Something went wrong while updating category in the table", categoryDAO.deleteCategory(category));
		assertEquals("Something went wrong while verifying the updated data from table", false, categoryDAO.get(2).isActive());
		
		// check for list operation

		assertEquals("Something went wrong while fetching active categories from the table", 1, categoryDAO.list().size());
	}
}

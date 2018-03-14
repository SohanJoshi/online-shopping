package com.shj.shoppingbackend.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.shj.shoppingbackend.dao.ProductDAO;
import com.shj.shoppingbackend.dto.Product;

public class ProductTestCase {

	private static AnnotationConfigApplicationContext context;
	private static ProductDAO productDAO;
	private static Product product;

	@Before
	public void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.shj.shoppingbackend");
		context.refresh();

		productDAO = context.getBean(ProductDAO.class);
	}

	@Test
	public void testCRUDProduct() {

		// create operation
		product = new Product();

		product.setName("Oppo Selfi S53");
		product.setBrand("Oppo");
		product.setDescription("This is selfie phone from Oppo");
		product.setCategoryId(2);
		product.setQuantity(5);
		product.setUnitPrice(12000);
		product.setSupplierId(3);
		product.setActive(true);

		assertTrue("Something went wrong while inserting a new product", productDAO.add(product));

		// reading and updating the category

		product = productDAO.get(2);
		assertEquals("Something went wrong while fetching product", "Samsung s7", product.getName());
		product.setName("Samsung Galaxy S7");
		assertEquals("Something went wrong while fetching product", true, productDAO.update(product));

		product = productDAO.get(2);
		assertEquals("Something went wrong while fetching product", "Samsung Galaxy S7", product.getName());

		// deleting a product

		product = productDAO.get(2);
		assertTrue("Somthing went wrong while deleting the product", productDAO.delete(product));

	}

	@Test
	public void testList() {
		// list
		assertEquals("Somthing went wrong while fetching the list active of products", 6, productDAO.list().size());
	}

	@Test
	public void testActiveList() {
		// list of active products
		assertEquals("Somthing went wrong while fetching the list active of products", 5,
				productDAO.listActiveProduct().size());
	}

	@Test
	public void testActiceListByCategory() {
		// list of active products by category

		assertEquals("Somthing went wrong while fetching the list active of products", 3,
				productDAO.listActiveProductByCategory(2).size());
	}

	@Test
	public void testActiveByCategoryToLimit() {
		// list of active products to limit

		assertEquals("Somthing went wrong while fetching the list active of products", 3,
				productDAO.getLatestActiveProduct(3).size());

	}
}

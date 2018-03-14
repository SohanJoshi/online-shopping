package com.shj.shoppingbackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shj.shoppingbackend.dao.ProductDAO;
import com.shj.shoppingbackend.dto.Product;

@Repository("productDAO")
@Transactional
public class ProductDAOImpl implements ProductDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Fetching Single product
	 */
	
	@Override
	public Product get(int productId) {
		return sessionFactory
				.getCurrentSession()
					.get(Product.class, Integer.valueOf(productId));
	}

	@Override
	public List<Product> list() {
		return sessionFactory
				.getCurrentSession()
					.createQuery("FROM Product", Product.class)
						.getResultList();
	}

	/**
	 * Insertion of new Product
	 */
	
	@Override
	public boolean add(Product product) {
		try {
			sessionFactory
					.getCurrentSession()
						.persist(product);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Update of Product
	 */

	@Override
	public boolean update(Product product) {
		try {
			sessionFactory
					.getCurrentSession()
						.update(product);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Delete a product / Disabling a product
	 */
	
	@Override
	public boolean delete(Product product) {
		product.setActive(false);
		try {
			return this.update(product);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Fetching an active list of products
	 */
	
	@Override
	public List<Product> listActiveProduct() {
		String selectActiveProducts = "FROM Product WHERE active = :active";		
		return sessionFactory
				.getCurrentSession()
					.createQuery(selectActiveProducts, Product.class)
						.setParameter("active", true)
							.getResultList();
	}

	/**
	 * Fetching an active list of Products by category
	 */
	
	@Override
	public List<Product> listActiveProductByCategory(int categoryId) {
		String selectActiveProductsByCategory = "FROM Product WHERE active = :active AND categoryId = :categoryId";		
		return sessionFactory
				.getCurrentSession()
					.createQuery(selectActiveProductsByCategory, Product.class)
						.setParameter("active", true)
							.setParameter("categoryId", Integer.valueOf(categoryId))
								.getResultList();
	}

	/**
	 * Fetching an active list of products to a limit count
	 */
	
	@Override
	public List<Product> getLatestActiveProduct(int count) {
		String selectActiveProducts = "FROM Product WHERE active = :active ORDER BY id";		
		return sessionFactory
				.getCurrentSession()
					.createQuery(selectActiveProducts, Product.class)
						.setParameter("active", true)
							.setFirstResult(0)
								.setMaxResults(count)
									.getResultList();
	}

}

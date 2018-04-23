package com.shj.shoppingbackend.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages={"com.shj.shoppingbackend.dto"})
@EnableTransactionManagement
public class HibernateConfig {

	private static final String DATABASE_URL = "jdbc:mysql://localhost/myonlineshopping";
	private static final String DATABASE_DRIVER_NAME = "com.mysql.jdbc.Driver";
	private static final String DATABSE_DIALECT = "org.hibernate.dialect.MySQLDialect";
	private static final String DATBASE_USER_NAME = "root";
	private static final String DATBASE_PASSWORD = "root";
	
	/**
	 * Generates the data source bean with the mentioned properties
	 * 
	 * @return DataSource bean object
	 */
	
	@Bean
	public DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		
		// Setting connection details
		
		dataSource.setDriverClassName(DATABASE_DRIVER_NAME);
		dataSource.setUrl(DATABASE_URL);
		dataSource.setUsername(DATBASE_USER_NAME);
		dataSource.setPassword(DATBASE_PASSWORD);
		
		return dataSource;
	}
	
	/**
	 * Creates the SessionFactory Bean using the DataSource object  
	 * @param dataSource
	 * @return SessionFactory bean
	 */
	@Bean
	public SessionFactory getSessionFactory(DataSource dataSource) {
		LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource);
		
		// Setting session factory properties
		
		builder.addProperties(getHibernateProperties());
		builder.scanPackages("com.shj.shoppingbackend.dto");
		
		return builder.buildSessionFactory();
	}

	/**
	 * Returns Properties object populated with Hibernate Properties
	 * @return Properties with Hibernate Properties
	 */
	private Properties getHibernateProperties() {
		Properties properties = new Properties();
		
		// Setting Hibernate Properties
		
		properties.put("hibernate.dialect", DATABSE_DIALECT);
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.format_sql", "true");
		
		return properties ;
	}
	
	/**
	 * 
	 * @param sessionFactory
	 * @return
	 */
	@Bean
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
		return transactionManager ;
	}
	
}

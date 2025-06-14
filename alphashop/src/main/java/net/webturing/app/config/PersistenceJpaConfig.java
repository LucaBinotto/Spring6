package net.webturing.app.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@PropertySource("classpath:hibernate.properties")
@EnableJpaRepositories("net.webturing.app.repository")
@EnableTransactionManagement
public class PersistenceJpaConfig {

	@Autowired
	private Environment env;

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan("net.webturing.app.entities");

		HibernateJpaVendorAdapter vendoradapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendoradapter);
		em.setJpaProperties(additionalProperties());

		return em;
	}
	
	@Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }


	@Bean
	public DataSource dataSource() {
		String DB_DRIVER_CLASS = env.getProperty("hibernate.connection.driver_class");
		String DB_PASSWORD = env.getProperty("hibernate.connection.password");
		String DB_USER = env.getProperty("hibernate.connection.username");
		String DB_URL = env.getProperty("hibernate.connection.url");

		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName(DB_DRIVER_CLASS);
		dataSource.setUrl(DB_URL);
		dataSource.setUsername(DB_USER);
		dataSource.setPassword(DB_PASSWORD);

		return dataSource;
	}

	Properties additionalProperties() {
		String HBM2DDL_AUTO = env.getProperty("hibernate.hbm2ddl.auto");
		String HIBERNATE_SHOW_SQL = env.getProperty("hibernate.show_sql");
		// String HIBERNATE_DIALECT = env.getProperty("hibernate.dialect");

		Properties properties = new Properties();
		properties.setProperty("hibernate.show_sql", HIBERNATE_SHOW_SQL);
		// properties.setProperty("hibernate.dialect", HIBERNATE_DIALECT);
		properties.setProperty("hibernate.connection.autocommit", "false");
		properties.setProperty("hibernate.hbm2ddl.auto", HBM2DDL_AUTO);

		return properties;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}
}

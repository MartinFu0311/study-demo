package demo.martin.config;

import java.beans.PropertyVetoException;
import java.io.DataOutput;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

//import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableJpaRepositories(basePackages="demo.martin.repository")
public class JPAConfig {
	/*@Bean
	public ComboPooledDataSource  dataSource()  {
		ComboPooledDataSource cpds = new ComboPooledDataSource();
		
		try {
			cpds.setDriverClass( "com.mysql.jdbc.Driver" ); //loads the jdbc driver            
			cpds.setJdbcUrl( "jdbc:mysql://localhost:3306/db_example?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false" );
			cpds.setUser("root");
			cpds.setPassword("Test123");
				
			// the settings below are optional -- c3p0 can work with defaults
			cpds.setMinPoolSize(5);                                     
			cpds.setAcquireIncrement(5);
			cpds.setMaxPoolSize(20);
		}
		catch (PropertyVetoException e) {
			// TODO: handle exception
		}		
			
		// The DataSource cpds is now a fully configured and usable pooled DataSource
		return cpds;
	}*/
	
	@Bean
	public BasicDataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/db_example?useUnicode=true&characterEncoding=utf8");
		dataSource.setUsername("root");
		dataSource.setPassword("Test123");
		dataSource.setMaxActive(5);
		dataSource.setInitialSize(1);
		
		return dataSource;
	}
	
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setDatabase(Database.MYSQL);
		jpaVendorAdapter.setGenerateDdl(true);
		jpaVendorAdapter.setShowSql(true);
		
		return jpaVendorAdapter;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setDataSource(dataSource());
		factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
		//factoryBean.setPackagesToScan("demo.martin.repository");  //注解 @EnableJpaRepositories 定义了 basePackages 属性，这里再定义同样的导致报错，报的错大致意思是这个类不是实体类
		factoryBean.setPackagesToScan("");
		
		Properties jpaProperties = new Properties();
		jpaProperties.setProperty("hibernate.format_sql", "true");
		jpaProperties.setProperty("hibernate.hbm2ddl.auto", "validate");
		factoryBean.setJpaProperties(jpaProperties);
		
		return factoryBean;
	}
	
	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}
}

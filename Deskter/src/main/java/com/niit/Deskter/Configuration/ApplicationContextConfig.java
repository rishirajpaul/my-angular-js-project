package com.niit.Deskter.Configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.Deskter.model.Blog;
import com.niit.Deskter.model.BlogComment;
import com.niit.Deskter.model.BlogLike;
import com.niit.Deskter.model.Forum;
import com.niit.Deskter.model.ForumComment;
import com.niit.Deskter.model.Friend;
import com.niit.Deskter.model.Job;
import com.niit.Deskter.model.JobApplication;
import com.niit.Deskter.model.Notification;
import com.niit.Deskter.model.UserDetails;


@Configuration
@ComponentScan("com.niit.Deskter")
@EnableTransactionManagement
public class ApplicationContextConfig {
	@Bean(name="dataSource")
	public DataSource getDataSource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
				/*--- Database connection settings ---*/
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");		//specify the driver...
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");		//specify the db_url...
		dataSource.setUsername("sa");		//specify the db_username...
		dataSource.setPassword("sa");		//specify the db_password...
		
		Properties connectionProperties = new Properties();
		connectionProperties.setProperty("hibernate.hbm2ddl.auto", "update");
		connectionProperties.setProperty("hibernate.show_sql", "true");
		connectionProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		connectionProperties.setProperty("hibernate.format_sql", "true");
		connectionProperties.setProperty("hibernate.jdbc.use_get_generated_keys", "true");
		//connectionProperties.setProperty("hibernate.default_schema", "BINDER");
		dataSource.setConnectionProperties(connectionProperties);		
		return dataSource;                                    // we are using oracle db for our project...
	}	
	
	@Autowired		//@Autowired annotation provides more fine-grained control over where and how autowiring should be accomplished..
	@Bean(name = "sessionFactory")			//sessionfactory creates the session for the application...
	public SessionFactory getSessionFactory(DataSource dataSource) {
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
		
		//specify all the model classes... 
		sessionBuilder.addAnnotatedClass(UserDetails.class);	
		sessionBuilder.addAnnotatedClass(Blog.class);
		sessionBuilder.addAnnotatedClass(BlogComment.class);
		sessionBuilder.addAnnotatedClass(JobApplication.class);	
        sessionBuilder.addAnnotatedClass(Job.class);
		sessionBuilder.addAnnotatedClass(Forum.class);	
		sessionBuilder.addAnnotatedClass(ForumComment.class);
		sessionBuilder.addAnnotatedClass(Friend.class);
		sessionBuilder.addAnnotatedClass(BlogLike.class);
		sessionBuilder.addAnnotatedClass(Notification.class);
			
		/*sessionBuilder.addAnnotatedClass(Friend.class);	
		sessionBuilder.addAnnotatedClass(FriendRequest.class);*/
		
		return sessionBuilder.buildSessionFactory();
	}
	
	@Autowired		//@Autowired annotation provides more fine-grained control over where and how autowiring should be accomplished..
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
	
	return transactionManager;
	}

}

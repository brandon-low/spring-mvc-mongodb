package hello.config;

import java.util.Locale;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
//import org.apache.commons.dbcp.BasicDataSource;
//import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
//import org.springframework.orm.hibernate4.HibernateTransactionManager;
//import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.LocaleResolver;

//http://docs.spring.io/spring-boot/docs/current/reference/html/howto-security.html
//Switch off the Spring Boot security configuration
//@EnableWebSecurity

// works with just @Configuration
@Configuration
//@ComponentScan({ "hello.*" })
//@EnableTransactionManagement
//@Import({ SpringSecurityConfig.class })
public class AppConfig {
	private static final Logger logger = LoggerFactory.getLogger(AppConfig.class);
	
		@Bean("messageSource")
	   public MessageSource messageSource() {
			logger.debug("*****INIT MESSAGE SOURCE *******");
	      ReloadableResourceBundleMessageSource messageSource=new ReloadableResourceBundleMessageSource();
	      messageSource.setBasename("classpath:locale/messages");
	      messageSource.setDefaultEncoding("UTF-8");
	      messageSource.setUseCodeAsDefaultMessage(true);
	      return messageSource;
	   }

	   
	   @Bean(name = "validator")
	   public LocalValidatorFactoryBean validator() {
		   logger.debug("*****INIT VALIDATOR WITh MESSAGE SOURCE *******");
	       LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
	       bean.setValidationMessageSource(messageSource());
	       return bean;
	   }
	   
	   @Bean
	   public LocaleResolver localeResolver() {
		   logger.debug("*****INIT LOCALE *******");
	      //CookieLocaleResolver localeResolver = new CookieLocaleResolver();
	      SessionLocaleResolver localeResolver = new SessionLocaleResolver();
	      localeResolver.setDefaultLocale(Locale.US);
	      return localeResolver;
	   }
	   
	   
	/*
	 @Bean
	 public MessageSource messageSource() {
	       ResourceBundleMessageSource source = new ResourceBundleMessageSource();
	       source.setBasename("messages");
	       return source;
	 }
	 @Bean
	 public LocaleResolver localeResolver() {
	      SessionLocaleResolver slr = new SessionLocaleResolver();
	        slr.setDefaultLocale(Locale.US);
	        return slr;
	  }
	  */ 

		// don't need it
	/*
	 * 
	 * @Override
public void addResourceHandlers(ResourceHandlerRegistry registry) {
registry.addResourceHandler("/resources/**")
    .addResourceLocations("/resources/");
}
	@Bean
    public SessionFactory sessionFactory() {
        LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource());
        builder
        	.scanPackages("com.mkyong.users.model")
            .addProperties(getHibernateProperties());

        return builder.buildSessionFactory();
    }

	private Properties getHibernateProperties() {
        Properties prop = new Properties();
        prop.put("hibernate.format_sql", "true");
        prop.put("hibernate.show_sql", "true");
        prop.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        return prop;
    }
	
	@Bean(name = "dataSource")
	public BasicDataSource dataSource() {
		
		BasicDataSource ds = new BasicDataSource();
	    ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/test");
		ds.setUsername("root");
		return ds;
	}
	
	@Bean
    public HibernateTransactionManager txManager() {
        return new HibernateTransactionManager(sessionFactory());
    }
		
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/pages/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	*/
	
}
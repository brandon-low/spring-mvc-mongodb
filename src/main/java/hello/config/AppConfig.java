package hello.config;

import java.util.Locale;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
//import org.apache.commons.dbcp.BasicDataSource;
//import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
//import org.springframework.orm.hibernate4.HibernateTransactionManager;
//import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;

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
	   
	   /*
	    * Internationalisation
	    */
	   @Bean
	   public LocaleResolver localeResolver() {
		   logger.debug("*****INIT LOCALE *******");
	      SessionLocaleResolver localeResolver = new SessionLocaleResolver();
	      localeResolver.setDefaultLocale(Locale.US);
	      return localeResolver;
	   }
	   
	   /**
	    * interceptor to change language
	    * @return
	    */
	   
	   @Bean
	   public LocaleChangeInterceptor localeChangeInterceptor() {
	       LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
	       lci.setParamName("lang");
	       return lci;
	   }
	  
	   
	   @Autowired
	   ApplicationContext applicationContext;
	   
	   //1. Creating SpringResourceTemplateResolver
	   @Bean
	   @Description("Thymeleaf Template Resolver")
	   public SpringResourceTemplateResolver springTemplateResolver(){
		   
		   logger.debug("****************Init springTemplateResolver *************");
		   
	       SpringResourceTemplateResolver springTemplateResolver = new SpringResourceTemplateResolver();
	       springTemplateResolver.setApplicationContext(this.applicationContext);
	       
	       springTemplateResolver.setPrefix("classpath:templates/");
	       springTemplateResolver.setSuffix(".html");
	       springTemplateResolver.setTemplateMode("HTML5");
	       
	       // default is true set to false
	       //springTemplateResolver.setCacheable(false);
	       
	       return springTemplateResolver;
	   }
	   
	   //2. Creating SpringTemplateEngine
	   @Bean
	   @Description("Thymeleaf Template Engine")
	   public SpringTemplateEngine springTemplateEngine(){
		   
		   logger.debug("****************Init springTemplateEngine *************");
		   
	       SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();
	       springTemplateEngine.setTemplateResolver(springTemplateResolver());
	       springTemplateEngine.setTemplateEngineMessageSource(messageSource());
	       return springTemplateEngine;
	   }
	   
	   
	   //3. Registering ThymeleafViewResolver
	   @Bean
	   @Description("Thymeleaf View Resolver")
	   public ViewResolver viewResolver(){
		   
		   logger.debug("****************Init viewResolver *************");
		   
	       ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
	       viewResolver.setTemplateEngine(springTemplateEngine());
	       viewResolver.setOrder(1);
	       return viewResolver;
	   }
	   
	   /*
	   
	   @Bean
	   @Description("Thymeleaf Template Resolver")
	   public ServletContextTemplateResolver templateResolver() {
	       ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(null);
	       
	       templateResolver.setPrefix("classpath:templates"); //  /WEB-INF/views/    /WEB-INF/templates/
	       templateResolver.setSuffix(".html");
	       templateResolver.setTemplateMode("HTML5");
	    
	       return templateResolver;
	   }
	   
	   @Bean
	   @Description("Thymeleaf Template Engine")
	   public SpringTemplateEngine templateEngine() {
	       SpringTemplateEngine templateEngine = new SpringTemplateEngine();
	       templateEngine.setTemplateResolver(templateResolver());
	       templateEngine.setTemplateEngineMessageSource(messageSource());
	       return templateEngine;
	   }
	   @Bean
	   @Description("Thymeleaf View Resolver")
	   public ThymeleafViewResolver viewResolver() {
	       ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
	       viewResolver.setTemplateEngine(templateEngine());
	       viewResolver.setOrder(1);
	       return viewResolver;
	   }
*/

	   
	   /**
	    * To Add
	    * 
	    * https://www.boraji.com/spring-mvc-5-hello-world-example-with-thymeleaf-template
	    */
	   /*


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
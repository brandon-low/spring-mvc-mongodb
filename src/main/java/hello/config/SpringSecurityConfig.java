package hello.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.AccessDeniedHandler;

import hello.service.UserService;

@Configuration
// http://docs.spring.io/spring-boot/docs/current/reference/html/howto-security.html
// Switch off the Spring Boot security configuration
//@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	 private static final Logger logger = LoggerFactory.getLogger(SpringSecurityConfig.class);

	@Autowired
	@Qualifier("userDetailsService")
	UserDetailsService userDetailsService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
    private AccessDeniedHandler accessDeniedHandler;
	
	@Autowired
	CustomSuccessHandler customSuccessHandler;
	
	@Autowired
    private CustomAuthenticationProvider authProvider;
	 

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		logger.debug("*********** Config GLOBAL Security User DetailsService ************");
		// works
		//auth.userDetailsService(userDetailsService).passwordEncoder(userService.getPasswordEncoder());
		
		// custom implementation
		auth.authenticationProvider(authProvider);
		
		//  auth.inMemoryAuthentication()
        //  .withUser("user").password("user").roles("USER")
        //  .and()
        //  .withUser("admin").password("admin").roles("ADMIN");
	}
		
    // roles admin allow to access /admin/**
    // roles user allow to access /user/**
    // custom 403 access denied handler
    @Override
    protected void configure(HttpSecurity http) throws Exception {

    	logger.debug("*********** Config Security ************");
        http.csrf().disable()
                .authorizeRequests()
	                .antMatchers("/", "/home", "/about").permitAll()
	                .antMatchers("/address/**", "/createuser", "/webjars/bootstrap/**").permitAll()
	                .antMatchers("/admin/**").hasAnyRole("ADMIN")
	                .antMatchers("/user/**").hasAnyRole("USER")
	                .anyRequest().authenticated()
	                .and()
		                .formLogin()
		                .loginPage("/login")
		                .failureUrl("/login?error")
		              	.defaultSuccessUrl("/", true)
				 		.usernameParameter("username")
				 		.passwordParameter("password")
		                .permitAll()
		            //.and().formLogin().loginPage("/login").successHandler(customSuccessHandler)
		            //    .usernameParameter("username").passwordParameter("password")
	                .and()
		                .logout()
		                .logoutSuccessUrl("/home")
		                .permitAll()
	                .and()
	                	.exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }


   

    /*
    //Spring Boot configured this already.
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }*/

}

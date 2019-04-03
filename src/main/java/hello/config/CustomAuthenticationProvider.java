package hello.config;
/**
 * sample format of custom authentication
 */

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import hello.entity.UserRole;
import hello.service.UserService;
/**
 * Make it an n tries lock out 
 * @author brandon
 *
 */

@Component
public class CustomAuthenticationProvider  implements AuthenticationProvider {
	
	 private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationProvider.class);
	 
	 
	@Autowired
	@Qualifier("userDetailsService")
	UserDetailsService userDetailsService;
	
	@Autowired
	private UserService userService;
	
	
	public CustomAuthenticationProvider() {
        super();
    }

	// Converts hello.entity.User user to
	// org.springframework.security.core.userdetails.User
	private User buildUserForAuthentication(hello.entity.User user, List<GrantedAuthority> authorities) {
		return new User(user.getUsername(), user.getPassword(), 
							user.isEnabled(), 
							user.isAccountNonExpired(), 
							user.isCredentialsNonExpired(), 
							user.isAccountNonLocked(), 
							authorities);
	}
		
	private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {
		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		// Build user's authorities
		for (UserRole userRole : userRoles) {
			setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
		}
		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);
			
		logger.info("UserRoles:" + userRoles + " has roles:" + Result);
		return Result;
	}
	
	
 
    @Override
    public Authentication authenticate(Authentication authentication) 
    				throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        logger.debug("authenticate  user:" + name + ":" + password);
        try {
        	hello.entity.User user = userService.getUserByUsername(name);
    		logger.debug("Found User for authen:" + name + " User:" + user);
    		
    		if (user == null ) {
    		
    			throw new UsernameNotFoundException("User :" + name + "not found");
    		} 
    		// account is locked
    		if (!user.isAccountNonLocked()) {
    			String msg = "User account is locked! Username : " + name +
    						" Last Attempts : " + user.getAttempts() +
    						" Time Last Tried: " + user.getLastModified();
    			userService.updateUserAttempt(name);
    			throw new LockedException(msg);
    		}
    		
    		if (!user.isAccountNonExpired()) {
    			String msg = "User account is locked! Username : " + name +
						" Last Attempts : " + user.getAttempts() +
						" Time Last Tried: " + user.getLastModified();
    			userService.updateUserAttempt(name);
    			throw new AccountExpiredException(msg);
    			
    		}
    		if (!user.isCredentialsNonExpired()) {
    			String msg = "User account is locked! Username : " + name +
						" Last Attempts : " + user.getAttempts() +
						" Time Last Tried: " + user.getLastModified();
				userService.updateUserAttempt(name);
				throw new CredentialsExpiredException(msg);
    		}
    		if (!user.isEnabled()) {
    			String msg = "User account is locked! Username : " + name +
						" Last Attempts : " + user.getAttempts() +
						" Time Last Tried: " + user.getLastModified();
				userService.updateUserAttempt(name);
				throw new DisabledException(msg);
    		}
    		if (user.getPassword() != null && userService.getPasswordEncoder().matches(password, user.getPassword())) {
	    		logger.debug("Found User " + user +" with matching name and password");
	    			
		    	List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRole());
		    	final UserDetails principal =  buildUserForAuthentication(user, authorities);
		    		
		    	// need to update the data store attempts and locked is
		    	userService.resetUserAttempt(name);
		    		
		    	return new UsernamePasswordAuthenticationToken(principal, password, authorities);
    		} else {
    			userService.updateUserAttempt(name);
    			throw new BadCredentialsException("Invalid username and password");
    		}
    		
        } catch (BadCredentialsException bce) {
        	logger.error("Caught BadCredentailException e:" + bce);
        	throw bce;
        } catch (LockedException le) {
        	logger.error("Caught LockedException e:" + le);
        	throw le;
        } catch (Exception e) {
        	logger.error("Caught Exception e:" + e);
        	throw e;
        }
      
    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
    
}

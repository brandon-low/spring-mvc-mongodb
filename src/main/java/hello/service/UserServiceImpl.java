package hello.service;


import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import hello.entity.User;
import hello.entity.UserRole;
import hello.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	UserRepository userRepository;
	
	private PasswordEncoder encoder;
	
	/** 
	 * Spring security use ADMIN while this is ROLE_ADMIN
	 */
	public static final String _ROLE_ADMIN 	= "ROLE_ADMIN";
	public static final String _ROLE_USER 	= "ROLE_USER";
	public static final String _ROLE_GUEST 	= "ROLE_GUEST";
	public static final String _ROLE_ANONYMOUS 	= "ROLE_ANONYMOUS";
	

	public void create(User user) {
		logger.info("save user=" + user);
		if (user != null) {
			user.setCreateTimestamp(new Date(System.currentTimeMillis()));
			user.setUpdateTimestamp(new Date(System.currentTimeMillis()));
		}
		userRepository.save(user);
	}

	@Override
	public void update(User user) {
        User userInDB = userRepository.findById(user.getId());
        
        logger.info("update from " + userInDB + " to " + user);
        userInDB.setUsername(user.getUsername() );
        BeanUtils.copyProperties(user, userInDB, "id" ); 
        
        if (userInDB != null) {
        	userInDB.setUpdateTimestamp(new Date(System.currentTimeMillis()));
		}
        userRepository.save(userInDB);
	}
	
	public void delete(String id) {
        User user = userRepository.findById(id);
        logger.info("delete user=" + user);
        if (user != null){
		    userRepository.delete(user);
        }
	}
	
	public List<User> getAllUsers() {
		return userRepository.findAll();

	}

	@Override
	public User getUserById(String id) {
		return userRepository.findById(id);
	}

	@Override
	public User getUserByUsername(String username) {
		logger.info("find user by username=" + username);
		return userRepository.findByUsername(username);
	}
	
	public List<User> findAllUserAccountNonLocked(boolean accountNonLocked){
		return userRepository.findAllUserAccountNonLocked(accountNonLocked);
	}
	
	private static final int MAX_ATTEMPT = 3; 
	public void updateUserAttempt(String username) {
		logger.info("Update UserAttempt username=" + username);
		User user = getUserByUsername(username);
		if (user == null) return;
		user.setAttempts( user.getAttempts() + 1);
		user.setLastModified(new Date(System.currentTimeMillis()));
		
		if (user.getAttempts() > MAX_ATTEMPT) {
			user.setAccountNonLocked(false);
		}
		update(user);
	}
	
	public void resetUserAttempt(User user) {
		user.setAttempts(0);
		user.setAccountNonLocked(true);
		user.setLastModified(null);
		update(user);
	}
	
	public void resetUserAttempt(String username) {
		User user = getUserByUsername(username);
		if (user == null) return;
		resetUserAttempt(user);
	}
	
	public void resetAllLockedUsers() {
		List<User> users = findAllUserAccountNonLocked(false);
		
		for (User user : users) {
			resetUserAttempt(user);
		}
	}
	
	public void lockAllUsers() {
		List<User> users = userRepository.findAll();
		
		for (User user : users) {
			user.setAccountNonLocked(false);
			update(user);
		}
	}
	
	
	public PasswordEncoder getPasswordEncoder(){
		if (encoder == null)  encoder = new BCryptPasswordEncoder();
		return encoder;
	}
	
	public boolean createUser(String username, String password, boolean enabled, 
			boolean accountNonExpired , boolean credentialsNonExpired, 
			boolean accountNonLocked, int attempts, HashSet<UserRole> roles) {
		boolean status = true;
		logger.info("createUser=" + username + " password=" + password);
		try {
			User user = userRepository.findByUsername(username);
			logger.info("createUser found user=" + user);
			
			if (user == null || user.getId() == null || user.getId().isEmpty() || user.getId().length() <= 0) {
				User u = new User();
				u.setEnabled(enabled);
				u.setUsername(username);
				u.setPassword(password);
				u.setUserRole(roles);
				u.setAccountNonExpired(accountNonExpired);
				u.setAccountNonLocked(accountNonLocked);
				u.setCredentialsNonExpired(credentialsNonExpired);
				u.setAttempts(attempts);
				
				// create it
				create(u);	
			} else {
				user.setEnabled(enabled);
				user.setPassword(password);
				user.setUserRole(roles);
				user.setAccountNonExpired(accountNonExpired);
				user.setAccountNonLocked(accountNonLocked);
				user.setCredentialsNonExpired(credentialsNonExpired);
				user.setAttempts(attempts);

				// uodate it
				update(user);
			}
			
		} catch (Exception e) {
			logger.error("Create User Exception", e);
			status = false;
		}
		return status;
	}
	
	
	public boolean createUser(String username, String password, HashSet<UserRole> roles) {
		return createUser(username, password, true, true, true, true, 0, roles);
	}
	
	public boolean createDefaultUsers() {
		boolean status = true;
		
		if (logger.isDebugEnabled()) {
			logger.debug("Create Default Users admin and user");
		}
		
		try {
			HashSet<UserRole> roles = new HashSet();
			roles.add(new UserRole(_ROLE_ADMIN));
			createUser("admin",getPasswordEncoder().encode("admin"), roles);
				
			HashSet<UserRole> roles2 = new HashSet();
			roles2.add(new UserRole(_ROLE_USER));
			createUser("user", getPasswordEncoder().encode("user"), roles2);
			
		} catch (Exception e) {
			logger.error("create DefaultUser Exception", e);
			status = false;
		}
		
		return status;
		
	}

}

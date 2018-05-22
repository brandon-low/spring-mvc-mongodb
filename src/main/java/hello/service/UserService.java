package hello.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;

//import hello.entity.Address;
import hello.entity.User;
import hello.entity.UserRole;

public interface UserService {

	void create(User user);
	void update(User user);
	void delete(String id);
	List<User> getAllUsers();
	User getUserById(String id);
	User getUserByUsername(String username);
	boolean createUser(String username, String password, boolean enabled, 
						boolean accountNonExpired , boolean credentialsNonExpired, 
						boolean accountNonLocked, int attempts, HashSet<UserRole> roles) ;
	boolean createDefaultUsers() ;
	PasswordEncoder getPasswordEncoder();
	List<User> findAllUserAccountNonLocked(boolean accountNonLocked);
	void updateUserAttempt(String username);
	void resetUserAttempt(User user);
	void resetUserAttempt(String username);
	void resetAllLockedUsers();
	void lockAllUsers();

}

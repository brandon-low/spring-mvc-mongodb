package hello.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//import hello.service.AddressService;
import hello.service.UserService;

@Controller
public class UserController {
	 private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	    @Autowired
	    UserService userService;
	    
	    @GetMapping("/createuser")
	    public String createUser() {
	    	logger.debug("Create User Page");
	    	try {
	    		userService.createDefaultUsers();
	    	} catch (Exception e) {
	    		logger.error(e.toString());
	    	}
	        return "/home";
	    }

}

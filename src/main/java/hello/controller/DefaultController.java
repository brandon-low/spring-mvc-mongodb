package hello.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DefaultController {
	 private static final Logger logger = LoggerFactory.getLogger(DefaultController.class);
	
	 	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	    public String homePage(ModelMap model) {
	 		if (logger.isDebugEnabled()) {
	    		logger.debug("Home Page");
	    	}
	 		
	        model.addAttribute("user", getPrincipal());
	        return "/home";
	    }
	 
	    @RequestMapping(value = "/admin", method = RequestMethod.GET)
	    public String adminPage(ModelMap model) {
	    	if (logger.isDebugEnabled()) {
	    		logger.debug("Admin Page");
	    	}
	    	
	        model.addAttribute("user", getPrincipal());
	        return "/test_admin";
	    }
	     
	    @RequestMapping(value = "/user", method = RequestMethod.GET)
	    public String userPage(ModelMap model) {
	    	
	    	if (logger.isDebugEnabled()) {
	    		logger.debug("User Page");
	    	}
	    	
	        model.addAttribute("user", getPrincipal());
	        return "/test_user";
	    }
	    
	    @RequestMapping(value = "/about", method = RequestMethod.GET)
	    public String aboutPage(ModelMap model) {
	    	
	    	if (logger.isDebugEnabled()) {
	    		logger.debug("About Page");
	    	}
	    	
	        model.addAttribute("user", getPrincipal());
	        return "/test_about";
	    }
	    
	    @GetMapping("/international")
	    public String getInternationalPage() {
	        return "test_international";
	    }
	 
	    @RequestMapping(value = {"/Access_Denied", "/403"}, method = RequestMethod.GET)
	    public String accessDeniedPage(ModelMap model) {
	    	if (logger.isDebugEnabled()) {
	    		logger.debug("Access Denied Page");
	    	}
	    	
	        model.addAttribute("user", getPrincipal());
	        return "/error/403";
	    }
	    
	    
	    
	  //customize the error message
		private String getErrorMessage(HttpServletRequest request, String key){
		
			Exception exception = (Exception) request.getSession().getAttribute(key);
			
			String error = "";
			if ((exception instanceof BadCredentialsException) || 
					(exception instanceof LockedException) ||
					exception instanceof UsernameNotFoundException) {
				error = exception.getMessage();
			} else {
				error = "Unknown System Error!";
			}
			
			return error;
		}
		
	   	 
	    @RequestMapping(value = "/login", method = RequestMethod.GET)
	    public String loginPage( @RequestParam(value = "error", required = false) String error,
	    						@RequestParam(value = "logout", required = false) String logout, 
	    						HttpServletRequest request, HttpServletResponse response, ModelMap model) {
	    	
	    	logger.debug("Login page error= " + error + " logout=" + logout);
	    	
	    	if (error != null) {
	    		model.addAttribute("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
	    		if (logger.isDebugEnabled())
	    			logger.debug("Login page set error =" + model.get("error"));	    	    
			}

			if (logout != null) {
				model.addAttribute("msg", "You've been logged out successfully.");
				if (logger.isDebugEnabled())
	    			logger.debug("Login page set logout =" + model.get("msg"));
			}
			
	        return "/login";
	    }
	 
	    @RequestMapping(value="/logout", method = RequestMethod.GET)
	    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        if (auth != null){    
	            new SecurityContextLogoutHandler().logout(request, response, auth);
	        }
	        return "redirect:/login?logout";
	    }
	 
	    private String getPrincipal(){
	        String userName = null;
	        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	 
	        if (principal instanceof UserDetails) {
	            userName = ((UserDetails)principal).getUsername();
	        } else {
	            userName = principal.toString();
	        }
	        return userName;
	    }
	    
	 
}

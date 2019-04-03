package hello.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import hello.service.ErrorMessageService;
//import hello.service.AddressService;
import hello.service.UserService;

@Controller
@RequestMapping("/setup")
public class UserController {
	 private static final Logger log = LoggerFactory.getLogger(UserController.class);

	    @Autowired
	    UserService userService;
	    
	    @Autowired
	    ErrorMessageService errorMessageService;
	    
	    private Locale locale;
	    
	    private String _page = "test_setup";
	    
	    public Locale getLocale() {
			return locale;
		}

	    @RequestMapping(value = "", method = RequestMethod.GET)
	    public String defaultPage(HttpServletRequest request, HttpServletResponse response,
	    								Locale locale, ModelMap model) {
	    	this.locale = locale;
	    	log.debug("Set Up Home Page locale=" + locale.toString() +
	    			" message=" + model.get("message"));
	    	
	    	if (model.get("message") == null) model.addAttribute("message", "Home page message");
	       
	    	return _page; 
	    }

	    
	    @GetMapping("/users")
	    public ModelAndView createUser(HttpServletRequest request, HttpServletResponse response,
	    							ModelMap model) {
	    	log.debug("Create Default Users");
	    	try {
	    		userService.createDefaultUsers();
	    	} catch (Exception e) {
	    		log.error(e.toString());
	    	}
	    	model.addAttribute("message", "create default user successfull");
		    
	    	
	    	// query string	
	    	return new ModelAndView( _page, model); 	
	    }
	    
	    @GetMapping("/testquery")
	    public String testquery(HttpServletRequest request, HttpServletResponse response,
	    							ModelMap model) {
	    	log.debug("testquery");
	    	
	    	
	    	// query string	
	    	return "redirect:/" + _page + "?message=create user successful!";
	    }
	    
	    /**
	     * Use redirect
	     * @return
	     */
	    @GetMapping("/testredirect")
	    public String redirectModelAndView(HttpServletRequest request, HttpServletResponse response,
	    						RedirectAttributes redirectAttributes) {
	    	log.debug("redirect page test");
	         
	    	redirectAttributes.addAttribute("message", "redirect success");
	    	//redirectAttributes.addFlashAttribute("fa", faValue);
	    		   
	    	//model.addAttribute("message", "redirect success");
	        return "redirect:/" + _page; 
	    }
	    
	    /**
	     * Use redirect
	     * @return
	     */
	    @GetMapping("/testforward")
	    public ModelAndView forwardModelAndView(HttpServletRequest request, HttpServletResponse response,
	    							ModelMap model) {
	    	log.debug("forward page test");
	    	  
	    	model.addAttribute("message", "forward success Prefix with message");
	        return new ModelAndView("forward:/" + _page, model); 	    }

}

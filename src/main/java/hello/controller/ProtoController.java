package hello.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import hello.entity.Proto;

/**
*	This is used to work out how to pass stuff around
*/

@Controller
@RequestMapping("/proto")
public class ProtoController {
	 private static final Logger log = LoggerFactory.getLogger(ProtoController.class);

	
	 private List<Proto> getProtos() {
		 List<Proto> list = new ArrayList();
		 
		 for (int i = 0; i < 5; i++) {
			 Proto e = new Proto();
			 e.setId("id=" + i);
			 e.setTitle("Title me good" + i);
			 e.setText("Message give me hell");
			 list.add(e);
		 }
		 
		 return list;
	 }
	 
	 private static String _page = "test_proto";
	 
	 @RequestMapping(value = "", method = RequestMethod.GET)
	 public String defaultPage(HttpServletRequest request, HttpServletResponse response,
	    							 ModelMap model) {
	         
		 log.debug("Proto Default");
		 return _page;  // works
	 }
	
	 // it's not working not loading the style sheets
	 /**
	  * changed from dev/proto.html to /dev/proto.html
	  * @param model
	  * @return
	  */
	 
	@RequestMapping(value = "message", method = RequestMethod.GET)
    public String messages(Model model) {
		log.debug("Proto message model");
        model.addAttribute("protos", getProtos()); //messageRepository.findAll());
        return _page;
    }
	
	@RequestMapping(value = "messagemv", method = RequestMethod.GET)
    public ModelAndView messagesMv() {
		log.debug("Proto message model and view");
        ModelAndView mav = new ModelAndView(_page);
        mav.addObject("protos",getProtos()) ;//  messageRepository.findAll());
        return mav;
    }
	
	@ModelAttribute("messages")
    public List<Proto> messages() {
       
		return getProtos();
    }

    @RequestMapping("redirect")
    public String redirect() {
    	log.debug("Proto redirect");
        return "redirect:/proto/message?q=Thymeleaf+Is+Great!+redirect";
    }
    
    @RequestMapping("forward")
    public String forward() {
    	log.debug("Proto forward");
        return "forward:/proto/message?q=Thymeleaf+Is+Great!+forward";
    }
    
}

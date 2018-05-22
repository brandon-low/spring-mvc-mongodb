package hello.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import hello.entity.Address;
import hello.service.AddressService;
import hello.service.ErrorMessageService;

import java.util.List;
import java.util.Locale;


import javax.validation.Valid;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/address")
public class AddressController {

    private static final Logger log = LoggerFactory.getLogger(AddressController.class);

    @Autowired
    AddressService addressService;
    
    @Autowired
    ErrorMessageService errorMessageService;
    
    private Locale locale;
    
    public Locale getLocale() {
		return locale;
	}

    
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String defaultPage(Locale locale, Model model) {
    	 this.locale = locale;
    	log.debug("Granny Home Page : redirect to Address Locale:" + locale.toString());
       
        return "address";
    }

	

	@RequestMapping(value = "/get/{_id}", method = RequestMethod.GET)
    public @ResponseBody Address get(@PathVariable String _id) {
        log.debug("in get method id=" + _id);
        return addressService.getAddressById(_id);
    }

    
    @RequestMapping(value = "/create/", method = RequestMethod.POST, consumes = "application/json")
    //@ResponseStatus(HttpStatus.CREATED)
    //public void create(@RequestBody Address address) {
    // added validation @ModelAttribute("address")
    public @ResponseBody JSONResponse create(@Valid @RequestBody Address address, BindingResult result) {
        log.debug("in create method " + address);

        JSONResponse response = new JSONResponse();
        // here is where we handle the error
        if(result.hasErrors()) {
        	response.setValidated(false);
        	response.setErrorMessages(errorMessageService.getDisplayErrorMessages(result, getLocale()));
        } else {
        
        	response.setValidated(true);
        	address.set_id(null);
        	addressService.createAddress(address);
        }
        return response;
    }


    @RequestMapping(value = "/update/", method = RequestMethod.PUT, consumes = "application/json")
    //@ResponseStatus(HttpStatus.OK)
    //public void update(@RequestBody Address address) {
    // added validation
    public @ResponseBody JSONResponse update(@Valid @RequestBody  Address address, BindingResult result) {
        log.debug("in update method " + address);
        JSONResponse response = new JSONResponse();
        
        // here is where we handle the error
        if(result.hasErrors()) {
        	response.setValidated(false);
        	log.debug("has Errors" + result);
        	response.setErrorMessages(errorMessageService.getDisplayErrorMessages(result, getLocale()));
        } else {
        	response.setValidated(true);
        	addressService.updateAddress(address);
        }
        return response;
    }

    @RequestMapping(value = "/delete/{_id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable String _id) {
        log.debug("in delete method id =" + _id);

        addressService.deleteAddress(_id);
    }

    @RequestMapping(value = "/addresses/", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<Address> list() {
        log.debug("in get all addresses method list");

        List<Address> addresses = addressService.getAllAddresses();
        
        log.debug("in get all addresses method list l=" + addresses.size());

        return addresses;
    }

}
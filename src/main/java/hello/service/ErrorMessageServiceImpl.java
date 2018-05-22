package hello.service;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

@Service
public class ErrorMessageServiceImpl implements ErrorMessageService {
	
	private static final Logger log = LoggerFactory.getLogger(ErrorMessageServiceImpl.class);
	

	private void logErrorMessages(BindingResult result) {
		if (!result.hasErrors() || !log.isDebugEnabled()) return ;
	
		log.debug("Has Errors=" + result.toString());
		
		List<ObjectError> objectErrors = result.getAllErrors();
		
		for (ObjectError objectError : objectErrors) {
			String[] codes = objectError.getCodes();
			
			String codeStr = "";
			for (String code: codes) {
				codeStr += "{" + code + "}";
			}
			
		    log.debug("[" +objectError.getCode() + "][" +
		    		"["+  codeStr +"][" +
		    		"["+  objectError.getObjectName() +"][" +
		    		"["+ objectError.getArguments() +"][" +
		    		"[" + objectError.getDefaultMessage()
		    		);
		}
		
	}
	
	
	public Map<String, String> getDisplayErrorMessages(BindingResult result, Locale locale) {
		if (!result.hasErrors()) return null;
		
		logErrorMessages(result);
		
		Map<String, String> errors = new HashMap<String, String>();
		List<FieldError> fieldErrors = result.getFieldErrors();
		for (FieldError field : fieldErrors) {
			log.debug("Error field=[" + field.getField() + 
						"] [" + field.getDefaultMessage()+
						"][" + field.getCode() +"]");
			errors.put(field.getField().toString(), field.getDefaultMessage().toString());
		}
		
		return errors;
	}
}

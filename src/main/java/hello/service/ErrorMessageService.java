package hello.service;

import java.util.Locale;
import java.util.Map;

import org.springframework.validation.BindingResult;

public interface ErrorMessageService {
	
	Map<String, String> getDisplayErrorMessages(BindingResult result, Locale locale);

}

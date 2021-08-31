package hello.config;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.CacheControl;
import org.springframework.ui.context.ThemeSource;
import org.springframework.ui.context.support.ResourceBundleThemeSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ThemeResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.theme.CookieThemeResolver;
import org.springframework.web.servlet.theme.ThemeChangeInterceptor;


//works with just @Configuration 
@Configuration			// works just with this uncommented
@EnableWebMvc			// activated to test , works as well
@ComponentScan("hello")	// activated this as well
public class MvcWebConfig implements WebMvcConfigurer {

	private static final Logger logger = LoggerFactory.getLogger(MvcWebConfig.class);

  
   @Override
   public void addInterceptors(InterceptorRegistry registry) {
	   logger.debug("****************Add Inceptor *************");
      ThemeChangeInterceptor themeChangeInterceptor = new ThemeChangeInterceptor();
      themeChangeInterceptor.setParamName("theme");
      registry.addInterceptor(themeChangeInterceptor);

      // change language
      LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
      localeChangeInterceptor.setParamName("lang");
      registry.addInterceptor(localeChangeInterceptor);
   }
   
   @Override
   public void addResourceHandlers(ResourceHandlerRegistry registry) {
       registry.addResourceHandler(
              "/webjars/**",
    		   "/resources/**",
               "/img/**",
               "/css/**",
               "/js/**")
               .addResourceLocations(
                      "classpath:/META-INF/resources/webjars/",
            		   "classpath:/resources/",
                       "classpath:/static/img/",
                       "classpath:/static/css/",
                       "classpath:/static/js/");
   }
   
   // added these
   
   /*
   @Override
   public void addResourceHandlers(ResourceHandlerRegistry registry) {
   registry.addResourceHandler("/resources/**")
       .addResourceLocations("/resources/");
   registry.addResourceHandler("/**")
   .addResourceLocations("/resources/js/sw.js");
   }
   */

}
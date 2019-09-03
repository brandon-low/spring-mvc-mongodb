package example.log;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TestLogslf4j {
	//Logger log= LogManager.getRootLogger();
	//Logger log = LogManager.getLogger(TestLog4j2.class);
	Logger log = LoggerFactory.getLogger(TestLogslf4j.class);
    //public static void main( String[] args ) {
	@Test
	public void test() {
	    
	    	//log.debug(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9);
	    	
	    	System.out.println("Configuration File Defined To Be :: "+
	    			System.getProperty("log4j.configurationFile"));
	    	
	    	log.trace("Trace Level test");
	    	log.debug("Debug lecel Test Me Good");
	    	log.info("Info level Test Me Good");
	    	log.warn("Warn LevelTest Me Good");
	    	log.error("Error Level Test Me Good");
	    	//log.fatal("Fatal Level Test");
	    	//log.trace("Configuration File Defined To Be :: "+
	    	//		System.getProperty("log4j.configurationFile"));
	    	
	    	
	    	System.out.println("End test");
    }
}

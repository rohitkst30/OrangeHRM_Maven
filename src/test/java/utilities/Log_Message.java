package utilities;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Log_Message {
	//Initialize Log4j instance
    private static Logger Log = 
		LogManager.getLogger(Log_Message.class.getName());
 
    public static void main(String[] args)throws IOException,SQLException{
    	//PropertiesConfigurator is used to configure logger from properties file
        //PropertyConfigurator.configure("D:\\F Drive\\Selenium Training Data\\workspace\\Log4jApp\\src\\test\\java\\log4j-html.properties");
    	
    	Log.debug("Hello this is a debug message");
    	Log.info("Hello this is an info message");
     }
    
    //We can use it when starting tests
    public static void startLog (String sTestCaseName){
        Log.info("Test is Starting...");
    }
 
    //We can use it when ending tests
    public static void endLog (String sTestCaseName){
        Log.info("Test is Ending...");
    }
 
    //Info Level Logs
    public static void info (String message) {
        Log.info(message);
    }
 
    //Warn Level Logs
    public static void warn (String message) {
        Log.warn(message);
    }
 
    //Error Level Logs
    public static void error (String message) {
        Log.error(message);
    }
 
    //Fatal Level Logs
    public static void fatal (String message) {
        Log.fatal(message);
    }
 
    //Debug Level Logs
    public static void debug (String message) {
        Log.debug(message);
    }
}

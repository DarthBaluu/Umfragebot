package Allgemein;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class Config {

	private static final String FILE = "config.properties";
	
	private static Properties prop;
	
	// no public constructor
	private Config() { }
	
	static {
		init();
	}
	
	public static String getProperty(Property name) {
		if (prop == null) reInit();
		if (prop == null) return null; // no settings available
		
	    // return the property value
	    return prop.getProperty(name.toString());
	}
	
	private static void init() {
		prop = new Properties();
		try {
		    // load a properties file from class path, inside static method
			if (!fileExists(FILE)) return;
			
			//InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(FILE);
			//InputStream stream = Config.class.getClassLoader().getResourceAsStream(FILE);
			InputStream stream = new FileInputStream(FILE);
		    prop.load(stream);
		}
		catch (IOException ex) {
		    ex.printStackTrace();
		}
	}
	
	private static boolean fileExists(String file) {
		File f = new File(file);
		return f.exists() && !f.isDirectory();
	}
	
	private static void reInit() {
		// for the present
		init();
	}
	
	public enum Property {
	    BotUser("botUser"),
	    BotToken("botToken"),

	    DB("db"),
	    DBUser("dbUser"),
	    DBPwd("dbPwd");
				
		private String name;
		
		private Property(String name) {
			this.name = name; 
		}
		
		@Override
		public String toString() {
			return name;
		}
	}
	
}

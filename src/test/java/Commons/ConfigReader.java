package Commons;

import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	
	private static Properties prop = new Properties();
	private String username;
	private String password;
	
	public String getappurl() throws IOException {
		prop.load(ConfigReader.class.getClassLoader().getResourceAsStream("configuration.properties"));
		String appurl = prop.getProperty("appurl");
		return appurl;
	}
	
	public String getusername() throws IOException {
	prop.load(ConfigReader.class.getClassLoader().getResourceAsStream("configuration.properties"));
	username = prop.getProperty("username");
	return username;
	}
	
	public String getpassword() throws IOException {
		prop.load(ConfigReader.class.getClassLoader().getResourceAsStream("configuration.properties"));
		password = prop.getProperty("password");
		return password;
		}
	
	public static String getportalurl() throws IOException {
		prop.load(BrowserFactory.class.getClassLoader().getResourceAsStream("configuration.properties"));
		String browserType = prop.getProperty("herokuappurl");
		return browserType;
	}
}

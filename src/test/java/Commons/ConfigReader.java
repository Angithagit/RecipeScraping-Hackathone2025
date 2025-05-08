package Commons;

import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	
	private static Properties prop = new Properties();
	
	public String getappurl() throws IOException {
		prop.load(ConfigReader.class.getClassLoader().getResourceAsStream("configuration.properties"));
		String appurl = prop.getProperty("appurl");
		return appurl;
	}

	public static String indianRecipeUrl() throws IOException {
		prop.load(BrowserFactory.class.getClassLoader().getResourceAsStream("configuration.properties"));
		return prop.getProperty("indianRecipeUrl");
	}
	
	public static String southIndianUrl() throws IOException {
		prop.load(BrowserFactory.class.getClassLoader().getResourceAsStream("configuration.properties"));
		return prop.getProperty("southIndianUrl");
	}

}

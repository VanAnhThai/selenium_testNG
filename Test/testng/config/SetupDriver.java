package testng.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SetupDriver {
	public static WebDriver buildSetupDriver() {
		String pathGecko = "/home/thai.thi.van.anh/Desktop/Autotest/chromedriver_linux64/chromedriver";
		System.setProperty("webdriver.gecko.driver", pathGecko);
		return (WebDriver) new ChromeDriver();
	}

}
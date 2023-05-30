package testng.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import testng.object.BasicObject;

import java.util.concurrent.TimeUnit;

import testng.config.SetupDriver;

public class VibloPageObjects extends BasicObject {

	String URL = "https://accounts.viblo.asia/login";

	public VibloPageObjects() throws Exception {

		this.driver = SetupDriver.buildSetupDriver();

		driver.get(URL); // Initialise Elements
		driver.manage().window().maximize(); // full screen
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public void sendValueForInputEmail(String email) {
		WebElement inputEmail = driver
				.findElement(By.xpath("/html/body/div/div/div/div/div/div/div[3]/form/div[1]/div/div/input"));
		inputEmail.sendKeys(email);
	}
}

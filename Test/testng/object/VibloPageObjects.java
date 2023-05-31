package testng.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import testng.object.BasicObject;

import java.util.concurrent.TimeUnit;

import testng.config.SetupDriver;

public class VibloPageObjects extends BasicObject {

	private String accessUrl = "https://accounts.viblo.asia/login";

	@FindBy(how = How.XPATH, using = "//input[@placeholder='Tên người dùng hoặc email']")
	private WebElement emailInput;

	@FindBy(how = How.XPATH, using = "//input[@type='password']")
	private WebElement passwordInput;

	@FindBy(how = How.XPATH, using = "/html/body/div/div/div/div/div/div/div[3]/button")
	public WebElement loginButton;

	@FindBy(how = How.XPATH, using = "/html/body/div[1]/div/div/div/div/div/div[3]/form/div[1]/div/div[2]")
	public WebElement emailError;

	@FindBy(how = How.XPATH, using = "/html/body/div[1]/div/div/div/div/div/div[3]/form/div[2]/div/div[2]")
	public WebElement passwordError;

	public VibloPageObjects() throws Exception {

		this.driver = SetupDriver.buildSetupDriver();
		this.driver.get(accessUrl);
		this.driver.manage().window().maximize();
		PageFactory.initElements(driver, this);
	}

//	public void sendValueForInputEmail(String email) {
//		WebElement inputEmail = driver
//				.findElement(By.xpath("/html/body/div/div/div/div/div/div/div[3]/form/div[1]/div/div/input"));
//		inputEmail.sendKeys(email);

	public void clickLoginButton() {
		loginButton.click();
	}
	public String emailInputError() {
		return emailError.getText();
	}

	public String passwordInputError() {
		return passwordError.getText();
	}

	public void quitDriver() {
		// TODO Auto-generated method stub
		
	}
}

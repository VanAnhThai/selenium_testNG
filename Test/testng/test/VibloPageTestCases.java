package testng.test;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import testng.object.VibloPageObjects;

public class VibloPageTestCases {

	VibloPageObjects vibloPage = null;

	public void initializeDriver() throws Exception {

// ----------- HomePage Class ----------
		vibloPage = new VibloPageObjects();
	}

// ----------- Test cases 1 ----------

	public void tc1_CheckLoginPage() throws Exception {

		String expectedTitle = "Viblo - Login";
		String actualTitle = vibloPage.driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle);

	}

	// ----------- Test cases 2 ----------

	public void tc2_CheckRequireAllitem() throws Exception {

		WebElement dangNhapButton = vibloPage.findTxtObject("ByXpath",
				"//button[@class='el-button w-100 el-button--primary']");
		WebDriverWait wait = new WebDriverWait(vibloPage.driver, 20);
		wait.until(ExpectedConditions.visibilityOf(dangNhapButton));
		vibloPage.clickButton(dangNhapButton);

		WebElement userError = vibloPage.findTxtObject("ByXpath",
				"//*[contains(text(),'Tên người dùng/email là bắt buộc')]");
		WebElement passError = vibloPage.findTxtObject("ByXpath", "//*[contains(text(),'Mật khẩu là bắt buộc')]");

		Assert.assertEquals(userError.getText(), "Tên người dùng/email là bắt buộc");
		Assert.assertEquals(passError.getText(), "Mật khẩu là bắt buộc");

		Thread.sleep(2000);
	}

// ----------- Test cases 3 ----------

	public void tc3_CheckRequireUserItem() throws Exception {

		WebElement dangNhapButton = vibloPage.findTxtObject("ByXpath",
				"//button[@class='el-button w-100 el-button--primary']");
		WebElement passWordTextBox = vibloPage.findTxtObject("ByXpath", "//input[@type='password']");
		vibloPage.sendKeyForObject(passWordTextBox, "abc123123");

		vibloPage.clickButton(dangNhapButton);

		WebElement userError = vibloPage.findTxtObject("ByXpath",
				"//*[contains(text(),'Tên người dùng/email là bắt buộc')]");
		Assert.assertEquals(userError.getText(), "Tên người dùng/email là bắt buộc");
		List<WebElement> listOfElements = vibloPage.driver
				.findElements(By.xpath("//div[@class='el-form-item__error']"));
		System.out.println("Number of elements:" + listOfElements.size());

		Assert.assertEquals(listOfElements.size(), 1);
		Thread.sleep(2000);

	}

// ----------- Test cases 4 ----------

	public void tc4_CheckRequirePassWordItem() throws Exception {

		WebElement dangNhapButton = vibloPage.findTxtObject("ByXpath",
				"//button[@class='el-button w-100 el-button--primary']");
		WebElement userTextBox = vibloPage.findTxtObject("ByXpath", "//input[@type='text']");
		WebElement passWordTextBox = vibloPage.findTxtObject("ByXpath", "//input[@type='password']");

		vibloPage.clearData(passWordTextBox);
		vibloPage.sendKeyForObject(userTextBox, "anhttv");
		vibloPage.clickButton(dangNhapButton);
		WebElement passError = vibloPage.findTxtObject("ByXpath", "//*[contains(text(),'Mật khẩu là bắt buộc')]");

		List<WebElement> listOfElements = vibloPage.driver
				.findElements(By.xpath("//div[@class='el-form-item__error']"));
		System.out.println("Number of elements:" + listOfElements.size());

		Assert.assertEquals(listOfElements.size(), 1);
		Assert.assertEquals(passError.getText(), "Mật khẩu là bắt buộc");

		Thread.sleep(2000);
	}

	@Test
	public void tC5_CheckLoginSuccess() throws Exception {

		WebElement dangNhapButton = vibloPage.findTxtObject("ByXpath",
				"//button[@class='el-button w-100 el-button--primary']");
		WebElement userTextBox = vibloPage.findTxtObject("ByXpath", "//input[@type='text']");
		WebElement passWordTextBox = vibloPage.findTxtObject("ByXpath", "//input[@type='password']");

		vibloPage.clearData(passWordTextBox);
		vibloPage.clearData(userTextBox);

		vibloPage.sendKeyForObject(userTextBox, "anhttv");
		vibloPage.sendKeyForObject(passWordTextBox, "abc123456");

		vibloPage.clickButton(dangNhapButton);

		WebDriverWait wait = new WebDriverWait(vibloPage.driver, 20);
		WebElement element = vibloPage.findTxtObject("ByXpath", "//*[contains(text(),'Chào mừng, Thai Thi Van Anh')]");

		wait.until(ExpectedConditions.visibilityOf(element));

		String expectedTitle = "Viblo Accounts";
		String actualTitle = vibloPage.driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle);

		Thread.sleep(2000);

	}
}

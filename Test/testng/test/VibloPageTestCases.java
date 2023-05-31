package testng.test;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import testng.object.VibloPageObjects;

public class VibloPageTestCases {

	VibloPageObjects vibloPage = null;

	@Test
	public void setupDriver() throws Exception {
		vibloPage = new VibloPageObjects();

	}

// ----------- Test cases 1 ----------

	@Test
	public void tc1_CheckLoginPageTest() throws Exception {
		String expectedTitle = "Viblo - Login";
		String actualTitle = vibloPage.driver.getTitle();
		AssertJUnit.assertEquals(actualTitle, expectedTitle);

//	có thể dùng	Assert.assertEquals(vibloPage.getTitle(), "Viblo - Login");
	}

// ----------- Test cases 2 ----------

	@Test
	public void tc2_CheckRequireAllitemTest() throws Exception {
		VibloPageObjects vibloPageOjects = new VibloPageObjects();
		vibloPageOjects.loginButton.click();
		Thread.sleep(500);
		AssertJUnit.assertEquals(vibloPage.emailInputError(), "User/email is requried");
		AssertJUnit.assertEquals(vibloPage.passwordInputError(), "Password is requried");
	}

// ----------- Test cases 3 ----------

	@Test
	public void tc3_CheckRequireUserTest() throws Exception {
		WebElement dangNhapButton = vibloPage.findTxtObject("ByXpath",
				"//button[@class='el-button w-100 el-button--primary']");
		WebElement passWordTextBox = vibloPage.findTxtObject("ByXpath", "//input[@type='password']");
		vibloPage.sendKeyForObject(passWordTextBox, "abc123456");

		vibloPage.clickButton(dangNhapButton);

		WebElement userError = vibloPage.findTxtObject("ByXpath",
				"//*[contains(text(),'Tên người dùng/email là bắt buộc')]");
		Assert.assertEquals(userError.getText(), "Tên người dùng/email là bắt buộc");
		List<WebElement> listOfElements = vibloPage.driver
				.findElements(By.xpath("//div[@class='el-form-item__error']"));
		Assert.assertEquals(listOfElements.size(), 1);
		Thread.sleep(2000);
	}

	// ----------- Test cases 4 ----------

		@Test
		public void tc4_CheckRequirePassWordTest() {
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
		}

	// ----------- Test cases 5 ----------

		@Test
		public void tc5_CheckLoginSuccessTest() throws Exception {
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

		}

		@BeforeSuite
		public void initializeDriver() throws Exception {
			vibloPage = new VibloPageObjects();
		}

		@AfterSuite
		public void quitDriver() {
			if (vibloPage.driver != null) {
				vibloPage.driver.quit();
			}
		}
	}

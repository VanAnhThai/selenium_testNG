package testng.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasicObject {
	public WebDriver driver;

	public WebElement findTxtObject(String typeFind, String valueFind) {
		WebElement txtObject = null;
		if (typeFind == "ByClassName") {
			txtObject = driver.findElement(By.className(valueFind));
		} else if (typeFind == "ById") {
			txtObject = driver.findElement(By.id(valueFind));
		} else if (typeFind == "ByName") {
			txtObject = driver.findElement(By.name(valueFind));
		} else if (typeFind == "ByXpath") {
			txtObject = driver.findElement(By.xpath(valueFind));
		} else if (typeFind == "ByCss") {
			txtObject = driver.findElement(By.cssSelector(valueFind));
		}
		return txtObject;
	}

	public void sendKeyForObject(WebElement element, String value) {
		element.sendKeys(value);
	}

	// event
	public void clickButton(WebElement element) {
		element.click();
	}

	public void clearDataThenSendValue(WebElement object, String value) {
		object.clear();
		object.sendKeys(value);
	}

	public void clearData(WebElement object) {
		object.clear();
	}
}

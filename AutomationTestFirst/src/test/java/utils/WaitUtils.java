package utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {
	public final int explicitWaitDefault = PropertyUtils.getIntegerProperty("explicitWait", 10);
	
	public void staticWait(final long millis) {
		try {
			Thread.sleep(millis);
		}catch (final InterruptedException e) {
			// TODO: handle exception
		}
	}
	
	public void waitForButtonToBeClickable(final WebElement element, final WebDriver driver) {
		new WebDriverWait(driver, this.explicitWaitDefault)
		.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void waitForElementToBePresent(final By locator, final WebDriver driver) {
		new WebDriverWait(driver, this.explicitWaitDefault)
		.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	
	

}

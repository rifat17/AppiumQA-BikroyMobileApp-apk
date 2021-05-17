package pageobject;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import utils.PropertyUtils;
import utils.WaitUtils;

public class BasePO {

	public static final int IMPLICIT_WAIT = PropertyUtils.getIntegerProperty("implicitWait", 30);
	WaitUtils waitUtils;

	protected final AppiumDriver driver;

	protected BasePO(AppiumDriver driver) {
		this.driver = driver;
		initElements();
		loadProperties();
		waitUtils = new WaitUtils();
	}

	private void loadProperties() {

	}

	private void initElements() {
		PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(IMPLICIT_WAIT)), this);

	}

	public void scrollDown() {

		Dimension currDimention = driver.manage().window().getSize();

		int startX = (int) (currDimention.width * 0.5);
		int startY = (int) (currDimention.height * 0.8);

		int endX = (int) (currDimention.width * 0.5);
		int endY = (int) (currDimention.height * 0.2);

//		System.out.println("StartXY(w,h) = (" + startX + "," + startY + ") EndXY(w,h) = (" + endX + "," + endY + ")");

		TouchAction touch = new TouchAction(driver);

		touch.press(PointOption.point(startX, startY)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(800)))
				.moveTo(PointOption.point(endX, endY)).release().perform();

	}

//
//	public void scrollDown(Dimension dimension) {
//
//	}
//
	public void scrollUp() {

		Dimension currDimention = driver.manage().window().getSize();

		int startX = (int) (currDimention.width * 0.5);
		int startY = (int) (currDimention.height * 0.2);

		int endX = (int) (currDimention.width * 0.5);
		int endY = (int) (currDimention.height * 0.8);

//		System.out.println("StartXY(w,h) = (" + startX + "," + startY + ") EndXY(w,h) = (" + endX + "," + endY + ")");

		TouchAction touch = new TouchAction(driver);

		touch.press(PointOption.point(startX, startY)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
				.moveTo(PointOption.point(endX, endY)).release().perform();

	}

	public void scrollToElement(final By locator, int waitTime) {

		for (int atteempt = 0; atteempt < waitTime; atteempt++) {
			try {
				WebElement element = driver.findElement(locator);
				if (element != null) {
					break;
				}
			} catch (Exception e) {
				driver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS);
			}
			scrollDown();
		}
//		waitUtils.waitForElementToBePresent(locator, driver);
	}

//
	public void scrollUp(Dimension other) {

		Dimension currDimention = driver.manage().window().getSize();

		int startX = (int) (currDimention.width * 0.5);
		int startY = (int) (currDimention.height * 0.2);

		int endX = (int) (currDimention.width * 0.5);
		int endY = (int) (startY + other.height);

//		System.out.println("StartXY(w,h) = (" + startX + "," + startY + ") EndXY(w,h) = (" + endX + "," + endY + ")");

		TouchAction touch = new TouchAction(driver);

		touch.press(PointOption.point(startX, startY)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
				.moveTo(PointOption.point(endX, endY)).release().perform();

	}

	public void scrollToText(String text) {
		((AndroidDriver) driver).findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""
						+ text + "\").instance(0))");
	}

}

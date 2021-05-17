package testcases;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import utils.PropertyUtils;
import utils.WaitUtils;

public abstract class BaseTestCase {
	public static AppiumDriver driver;
	public final static String APPIUM_SERVER_URL = PropertyUtils.getProperty("appium.server.url",
			"http://127.0.0.1:4723/wd/hub");
	public final static int IMPLICIT_WAIT = PropertyUtils.getIntegerProperty("implicitWait", 30);
	public static WaitUtils waitUtils = new WaitUtils();

	@BeforeMethod
	public void setUpAppium() throws MalformedURLException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		setDesiredCapabilitiesForAndroid(capabilities);
		driver = new AndroidDriver<WebElement>(new URL(APPIUM_SERVER_URL), capabilities);
	}

	@BeforeTest
	public abstract void setUpPage();

	@AfterMethod(alwaysRun = true)
	public void afterMethod(final ITestResult result) {
		String filename = result.getTestClass().getName() + "_" + result.getName();
		System.out.println("Test Case : [" + filename + "] executed..!");
		quitDriver();
	}

//	@AfterSuite
//	public void tearDownAppium() {
//		System.out.println("tearDownAppium - @AfterSuite..!");
//		quitDriver();
//	}

	private void quitDriver() {
		try {
			this.driver.quit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void setDesiredCapabilitiesForAndroid(DesiredCapabilities capabilities) {
		String PLATFORM_NAME = PropertyUtils.getProperty("android.platform");
		String PLATFORM_VERSION = PropertyUtils.getProperty("android.platform.version");
		String APP_NAME = PropertyUtils.getProperty("android.app.name");
		String APP_RELATIVE_PATH = PropertyUtils.getProperty("android.app.location") + APP_NAME;
		String APP_PATH = getAbsolutePath(APP_RELATIVE_PATH);
		String DEVICE_NAME = PropertyUtils.getProperty("android.device.name");
		String APP_PACKAGE_NAME = PropertyUtils.getProperty("android.app.packageName");
		String APP_ACTIVITY_NAME = PropertyUtils.getProperty("android.app.activityName");
		String APP_FULL_RESET = PropertyUtils.getProperty("android.app.full.reset");
		String APP_NO_RESET = PropertyUtils.getProperty("android.app.no.reset");

		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, DEVICE_NAME);
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, PLATFORM_NAME);
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, PLATFORM_VERSION);
		capabilities.setCapability(MobileCapabilityType.APP, APP_PATH);
		capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, APP_PACKAGE_NAME);
		capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, APP_ACTIVITY_NAME);
		capabilities.setCapability(MobileCapabilityType.FULL_RESET, APP_FULL_RESET);
		capabilities.setCapability(MobileCapabilityType.NO_RESET, APP_NO_RESET);
		capabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);

	}

	public static WebDriver getScreenshottableWebDriver() {
		final WebDriver augmentedDriver = new Augmenter().augment(driver);
		return augmentedDriver;
	}

	private static void setTimeOuts(AppiumDriver driver) {
		driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
	}

	private static String getAbsolutePath(String appRelativePath) {
		File file = new File(appRelativePath);
		return file.getAbsolutePath();
	}

	@AfterMethod
	public void tearDown() {
		System.out.println("After method executed..!");
	}
}

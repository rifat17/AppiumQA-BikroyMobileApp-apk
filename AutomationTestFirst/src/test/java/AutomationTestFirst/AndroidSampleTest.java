package AutomationTestFirst;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class AndroidSampleTest {
	
	public AndroidDriver driver;
	
	@BeforeTest
	public void setUp() throws MalformedURLException {
		String appiumServerURL = "http://127.0.0.1:4723/wd/hub";
		
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, "5.1.1");
		dc.setCapability(MobileCapabilityType.APP, "/home/hasib/Downloads/Bikroy.apk");
		dc.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
		dc.setCapability(MobileCapabilityType.NO_RESET, "false");
		dc.setCapability(MobileCapabilityType.FULL_RESET, "false");
		
		driver = new AndroidDriver(new URL(appiumServerURL), dc);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
//		loadHomePage();
		
	}
	
	private void loadHomePage() {
		WebElement localElement = driver.
				findElement(By.id("com.bikroy:id/intro_locale_picker")).
				findElement(By.className("android.widget.TextView"));
		
		String local = localElement.getText();
		System.out.println("LOCAL" + local);
		
		if(local.equalsIgnoreCase("English")) {
			localElement.click();
		}
		
		driver.findElement(By.id("com.bikroy:id/intro_find_ads")).click();
		
	}

	@Test
	public void firstTest() {
		
		String selector = "new UiSelector().text(\"English\").className(\"android.widget.TextView\")";
		MobileElement element = (MobileElement)driver.findElement(MobileBy.AndroidUIAutomator(selector));
		System.out.println(element.getText());
	}

}

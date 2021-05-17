package pageobject;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class PropertyPO extends BasePO {

	public PropertyPO(AppiumDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	By roomRentalLocator = MobileBy.xpath("//android.widget.TextView[@text=\"Room Rentals\"]");
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Room Rentals\"]")
	AndroidElement roomRentals;
	
	public void scrollToElementByText(String text) {
//		scrollToText(text);
		scrollToElement(roomRentalLocator, 5);
	}
	
	public void tapOnRoomRentals() {
		roomRentals.click();
	}
	

}

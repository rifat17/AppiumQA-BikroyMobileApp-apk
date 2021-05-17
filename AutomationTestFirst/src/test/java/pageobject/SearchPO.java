package pageobject;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class SearchPO extends BasePO {

	public SearchPO(AppiumDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Property\"]") // [@text()=\"Property\"]
	AndroidElement property;

	public void tapOnProperty() {
		property.click();
	}

}

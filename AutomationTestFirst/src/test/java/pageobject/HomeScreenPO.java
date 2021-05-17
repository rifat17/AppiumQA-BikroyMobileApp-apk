package pageobject;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class HomeScreenPO extends BasePO {

	public HomeScreenPO(AppiumDriver driver) {
		super(driver);

	}

//	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"English\").className(\"android.widget.TextView\")")
//	AndroidElement setLocalBtn;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='English']")
	AndroidElement localTextView;

	@AndroidFindBy(id = "com.bikroy:id/intro_find_ads")
	AndroidElement introFindAdsFrameLayout;

	public void setLocalToEnglish() {
		localTextView.click();
	}

	public void introFindAds() {
		introFindAdsFrameLayout.click();
	}

}

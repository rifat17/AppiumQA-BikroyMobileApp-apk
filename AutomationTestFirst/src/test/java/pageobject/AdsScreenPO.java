package pageobject;

import java.util.List;
import java.util.Random;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AdsScreenPO extends BasePO {

	public AdsScreenPO(AppiumDriver driver) {
		super(driver);
	}

	@AndroidFindBy(xpath = "//androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout")
	List<AndroidElement> ads;

	@AndroidFindBy(id = "com.bikroy:id/main_bottom_panel_search")
	AndroidElement searchBtn;

	@AndroidFindBy(id = "com.bikroy:id/main_bottom_panel_my_account")
	AndroidElement profileBtn;

	public void tapOnSpicificAd() {
//		System.out.println(ads.size());
		ads.get(new Random().nextInt(ads.size())).click();
	}

	public void tapOnSearchButton() {
		searchBtn.click();
	}

	public void tapOnProfileButton() {
		profileBtn.click();
	}

}

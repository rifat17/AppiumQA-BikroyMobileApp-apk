package pageobject;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class MyProfilePO extends BasePO {

	public MyProfilePO(AppiumDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@AndroidFindBy(id = "com.bikroy:id/my_resume_create_resume")
	AndroidElement createProfileBtn;
	
	public void tapOnCreateProfile() {
		createProfileBtn.click();
	}

}

package pageobject;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AccountPO extends BasePO {

	public AccountPO(AppiumDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@AndroidFindBy(id = "com.bikroy:id/sign_in_sign_up_email_button")
	AndroidElement continueWithEmailBtn;

	@AndroidFindBy(id = "com.bikroy:id/my_account_my_resume")
	AndroidElement myProfile;

	public void tapOnContinueWithEmail() {
		continueWithEmailBtn.click();
	}

	public void tapOnMyProfile() {
		myProfile.click();
	}

}

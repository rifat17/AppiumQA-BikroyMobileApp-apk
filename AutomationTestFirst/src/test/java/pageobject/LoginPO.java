package pageobject;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class LoginPO extends BasePO {

	public LoginPO(AppiumDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@AndroidFindBy(id = "com.bikroy:id/sign_in_email")
	AndroidElement signInEmail;
	
	@AndroidFindBy(id = "com.bikroy:id/sign_in_password")
	AndroidElement signInPassword;

	@AndroidFindBy(id = "com.bikroy:id/register_register_button")
	AndroidElement signInSubmit;

	public void typeEmail(String email) {
		signInEmail.clear();
		signInEmail.sendKeys(email);
	}
	
	public void typePassword(String passwd) {
		signInPassword.clear();
		signInPassword.sendKeys(passwd);
	}
	
	public void clickSignIn() {
		signInSubmit.click();
	}

}

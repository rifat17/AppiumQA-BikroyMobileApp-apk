package pageobject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class BasicInformationPO extends BasePO {

	public BasicInformationPO(AppiumDriver driver) {
		super(driver);
	}

	@AndroidFindBy(xpath = "//android.widget.LinearLayout[@content-desc=\"name\"]/*//android.widget.EditText")
	AndroidElement nameEditText;

	@AndroidFindBy(xpath = "//android.widget.LinearLayout[@content-desc=\"phone_number\"]/*//android.widget.EditText")
	AndroidElement phoneEditText;

	@AndroidFindBy(xpath = "//android.widget.RadioButton[@text=\"Male\"]")
	AndroidElement genderMaleRatioBtn;

	@AndroidFindBy(xpath = "//android.widget.RadioButton[@text=\"Female\"]")
	AndroidElement genderFemaleRatioBtn;

	@AndroidFindBy(xpath = "//android.widget.RadioButton[@text=\"Other\"]")
	AndroidElement genderOtherRatioBtn;

	@AndroidFindBy(xpath = "//android.widget.LinearLayout[@content-desc=\"birth_date\"]/android.widget.FrameLayout/android.widget.TextView")
	AndroidElement currentBirthDate;

	@AndroidFindBy(xpath = "//android.widget.NumberPicker[1]/android.widget.Button[1]")
	AndroidElement monthPrevBtn;
	@AndroidFindBy(xpath = "//android.widget.NumberPicker[1]/android.widget.Button[2]")
	AndroidElement monthNextBtn;

	@AndroidFindBy(xpath = "//android.widget.NumberPicker[2]/android.widget.Button[1]")
	AndroidElement dayPrevBtn;
	@AndroidFindBy(xpath = "//android.widget.NumberPicker[2]/android.widget.Button[2]")
	AndroidElement dayNextBtn;

	@AndroidFindBy(xpath = "//android.widget.NumberPicker[3]/android.widget.Button[1]")
	AndroidElement yearPrevBtn;
	@AndroidFindBy(xpath = "//android.widget.NumberPicker[3]/android.widget.Button[2]")
	AndroidElement yearNextBtn;

	@AndroidFindBy(id = "com.bikroy:id/dialog_date_picker_set")
	AndroidElement datePickerSetBtn;

	@AndroidFindBy(id = "com.bikroy:id/dialog_date_picker_cancel")
	AndroidElement datePickerCancelBtn;

	@AndroidFindBy(id = "com.bikroy:id/spinner_view")
	AndroidElement livingIn;
	@AndroidFindBy(id = "com.bikroy:id/search_l2_location")
	AndroidElement locationSearchEditText;
	@AndroidFindBy(id = "com.bikroy:id/location_filter_recycler_view")
	AndroidElement suggestedLocations;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Back to All locations']")
	AndroidElement backToAllLocationBtn;

	@AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc=\"Navigate up\"]")
	AndroidElement navigateUp;

	@AndroidFindBy(xpath = "//android.widget.LinearLayout[@content-desc=\"education_level\"]/*//android.widget.Spinner")
	AndroidElement higherEducationLevelSpinner;
	// only works after tapOnHigherEducationLevel() is called
	@AndroidFindBy(xpath = "//android.widget.ListView/android.widget.TextView")
	List<AndroidElement> supportedEduValues;

	@AndroidFindBy(xpath = "//android.widget.LinearLayout[@content-desc=\"current_role\"]/*//android.widget.EditText")
	AndroidElement currentJob;

	@AndroidFindBy(xpath = "//android.widget.LinearLayout[@content-desc=\"experience\"]/*//android.widget.EditText")
	AndroidElement totalYrsOfWorkExp;

	@AndroidFindBy(id = "com.bikroy:id/my_resume_personal_save")
	AndroidElement footerContinueBtn;

	public void typeName(String name) {
		nameEditText.clear();
		nameEditText.sendKeys(name);
	}

	public void typePhoneNumber(String phoneNumber) {
		phoneEditText.clear();
		phoneEditText.sendKeys(phoneNumber);
	}

	public void tapOnGender(String genderTtype) {
		if ("Male".equalsIgnoreCase(genderTtype)) {
			genderMaleRatioBtn.click();
		} else if ("Female".equalsIgnoreCase(genderTtype)) {
			genderFemaleRatioBtn.click();
		} else {
			genderOtherRatioBtn.click();
		}
	}

	public void tapOnBirthDatePicker() {
		currentBirthDate.click();
	}

	public void tapOnDatePickerSetBtn() {
		datePickerSetBtn.click();
	}

	public void tapOnDatePickerCancelBtn() {
		datePickerCancelBtn.click();
	}

	public String getCurrentBirthDate() {
		return currentBirthDate.getText();
	}

	public void setDateOfBirth(String dateOfBirthString) {

		/*
		 * until date of birth is unset, currentBirthDate.getText() will return
		 * DD/MM/YYYY, after tapOnBirthDatePicker(), default is set as
		 * currentDayofMonth/currentMonth/2007
		 *
		 */

		String currentDateString = getCurrentBirthDate();
		if ("DD/MM/YYYY".equalsIgnoreCase(currentDateString)) {
			tapOnBirthDatePicker();
			waitUtils.staticWait(500);
			tapOnDatePickerSetBtn();
		}

		currentDateString = getCurrentBirthDate();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate currDate = LocalDate.parse(currentDateString, formatter);
		LocalDate newDate = LocalDate.parse(dateOfBirthString, formatter);
		// check newDate.isBefore(currentDate)
		tapOnBirthDatePicker();
		try {
			setBdayYearField(currDate, newDate);
			waitUtils.staticWait(1000);

			setBdayDayField(currDate, newDate);

			waitUtils.staticWait(1000);

			setBdayMonthField(currDate, newDate);

		} catch (Exception e) {
			// TODO: handle exception
		}

		tapOnDatePickerSetBtn();
		waitUtils.staticWait(1000);

	}

	private void setBdayMonthField(LocalDate currDate, LocalDate newDate) {
		int monthDiff = currDate.getMonthValue() - newDate.getMonthValue();
		AndroidElement monthBtn = null;
		if (monthDiff > 0) {
			monthBtn = monthPrevBtn;
		} else {
			monthBtn = monthNextBtn;
			monthDiff *= -1;
		}

		while (monthDiff > 0) {
			monthBtn.click();
			monthDiff--;
		}
	}

	private void setBdayDayField(LocalDate currDate, LocalDate newDate) {
		int dayDiff = currDate.getDayOfMonth() - newDate.getDayOfMonth();

		AndroidElement dayBtn = null;
		if (dayDiff > 0) {
			dayBtn = dayPrevBtn;
		} else {
			dayBtn = dayNextBtn;
			dayDiff *= -1;
		}

		while (dayDiff > 0) {
			dayBtn.click();
			dayDiff--;
		}
	}

	private void setBdayYearField(LocalDate currDate, LocalDate newDate) {
		int yearDiff = currDate.getYear() - newDate.getYear();
		AndroidElement yearBtn = null;
		if (yearDiff > 0) {
			yearBtn = yearPrevBtn;
		} else {
			yearBtn = yearNextBtn;
			yearDiff *= -1;
		}

		while (yearDiff > 0) {
			yearBtn.click();
			yearDiff--;
		}
	}

	public void tapOnLivingIn() {
		livingIn.click();
	}

	public String getLivingInCurrentLocationStr() {
		return livingIn.getText();
	}

	public void tapOnNavigateUp() {
		navigateUp.click();
	}

	public void tapOnBackToAllLocation() {
		backToAllLocationBtn.click();
	}

	public void typeLivingInLocation(String location) {
		locationSearchEditText.clear();
		locationSearchEditText.sendKeys(location);
	}

	public List<MobileElement> getSuggestedLocations() {
		return suggestedLocations.findElementsById("com.bikroy:id/search_item_autocomplete");
	}

	public void setLivingInLocation(String livingLocation) {
		String currLivingLoc = getLivingInCurrentLocationStr();
		boolean tappedOnLivingIn = false;

		// correct location is set!
		if (currLivingLoc.equalsIgnoreCase(livingLocation)) {
			System.out.println("correct already location is set!");
			return;
		}
		// location is set but incorrect, needs to change
		else if (!currLivingLoc.equalsIgnoreCase("Select a location")) {
			tapOnLivingIn();
			System.out.println("tapOnLivingIn() called!");
			tappedOnLivingIn = true;
			waitUtils.staticWait(1000);
			tapOnBackToAllLocation();
		}

		// location is never set or tapOnNavigateUp() called
		System.out.println("####### Assuming Correct Location is Provided to be set #######");

		if (!tappedOnLivingIn)
			tapOnLivingIn();

		typeLivingInLocation(livingLocation);
		getSuggestedLocations().get(0).click(); // set location, Assuming Correct Location is Provided.

	}

	public void setHigherEducationLevel(String educationLevel) {

		String currValue = getCurrEdulactionLevel();

		if (educationLevel.equalsIgnoreCase(currValue)) {
			System.out.println("Already set!");
			return;
		}

		tapOnHigherEducationLevel();

		for (AndroidElement eduLevel : supportedEduValues) {
			if (educationLevel.equalsIgnoreCase(eduLevel.getText())) {
				eduLevel.click();
				break;
			}
		}

	}

	private String getCurrEdulactionLevel() {
		String currValue = higherEducationLevelSpinner.findElementByClassName("android.widget.TextView").getText();
		return currValue;
	}

	public void tapOnHigherEducationLevel() {
		higherEducationLevelSpinner.click();

	}

	// TODO: fix bug, could not detect popup window, tried uiautomator,appium inspect but both failed.
	public void setCurrentJob(String job) {

		System.out.println(currentJob.getText());

//		String jobPrefix = (String) job.substring(0, job.length() - 2);
		currentJob.clear();
		currentJob.click();
		currentJob.sendKeys(job);
//		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
		driver.hideKeyboard();

	}

	public void setTotalYrsOfWorkExp(String years) {
		totalYrsOfWorkExp.clear();
		totalYrsOfWorkExp.sendKeys(years);
	}

	public void tapOnContinue() {
		footerContinueBtn.click();
	}

}

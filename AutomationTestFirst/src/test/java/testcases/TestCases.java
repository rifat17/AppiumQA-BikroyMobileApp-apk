package testcases;

import static org.junit.Assert.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageobject.AccountPO;
import pageobject.AdDetailPO;
import pageobject.AdsScreenPO;
import pageobject.BasicInformationPO;
import pageobject.HomeScreenPO;
import pageobject.LoginPO;
import pageobject.MyProfilePO;
import pageobject.PropertyPO;
import pageobject.SearchPO;
import utils.PropertyUtils;

public class TestCases extends BaseTestCase {

	private static final int STATIC_WAIT = 1000;

	@BeforeMethod
	@Override
	public void setUpPage() {
		System.out.println("-------setUpPage-------");
		HomeScreenPO homeScreenPO = new HomeScreenPO(driver);
		try {
			homeScreenPO.setLocalToEnglish();
		} catch (Exception e) {
			e.printStackTrace();
		}
		homeScreenPO.introFindAds();

	}

//	@Ignore
	@Test
	public void TestCase_01() {
		System.out.println("Test1");
		AdsScreenPO adsScreenPO = new AdsScreenPO(driver);
		waitUtils.staticWait(STATIC_WAIT * 2); // loads ads, network call
		adsScreenPO.scrollDown();
		adsScreenPO.tapOnSpicificAd();
		waitUtils.staticWait(STATIC_WAIT); // loads single ad, network call

		AdDetailPO adDetailPO = new AdDetailPO(driver);
		assertTrue(adDetailPO.isAdTitleDisplayed());
		assertTrue(adDetailPO.isAdPriceDisplayed());

		adDetailPO.loadSimilarAds();

		assertTrue(adDetailPO.isSimilarAdsDisplayed());
		waitUtils.staticWait(STATIC_WAIT * 2);
		try {
			for (int i = 0; i < 2; i++) { // 10
				adDetailPO.getLowestPriceElement().click();
				waitUtils.staticWait(STATIC_WAIT * 2);

				assertTrue(adDetailPO.isAdTitleDisplayed());
				assertTrue(adDetailPO.isAdPriceDisplayed());

				adDetailPO.loadSimilarAds();
				assertTrue(adDetailPO.isSimilarAdsDisplayed());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	@Ignore
	@Test
	public void TestCase_02() {
		AdsScreenPO adsScreenPO = new AdsScreenPO(driver);
		adsScreenPO.tapOnSearchButton();
		waitUtils.staticWait(STATIC_WAIT * 2);

		SearchPO searchPO = new SearchPO(driver);
		searchPO.tapOnProperty();
		waitUtils.staticWait(STATIC_WAIT * 2);

		PropertyPO propertyPO = new PropertyPO(driver);
		propertyPO.scrollToElementByText("Room Rentals");
		propertyPO.tapOnRoomRentals();
		waitUtils.staticWait(STATIC_WAIT * 2);

		adsScreenPO.tapOnSpicificAd();
		waitUtils.staticWait(STATIC_WAIT * 2);

		AdDetailPO adDetailPO = new AdDetailPO(driver);
		assertTrue(adDetailPO.isAdTitleDisplayed());
		assertTrue(adDetailPO.isAdPriceDisplayed());

		adDetailPO.loadSimilarAds();
		adDetailPO.getLowestPriceElement().click();
		waitUtils.staticWait(STATIC_WAIT * 2);

	}

	@Test
	public void TestCase_03() {
		AdsScreenPO adsScreenPO = new AdsScreenPO(driver);
		adsScreenPO.tapOnProfileButton();
		waitUtils.staticWait(STATIC_WAIT * 2);

		AccountPO accountPO = new AccountPO(driver);
		accountPO.tapOnContinueWithEmail();
		waitUtils.staticWait(STATIC_WAIT * 2);

		LoginPO loginPO = new LoginPO(driver);
		String email = PropertyUtils.getProperty("login2.email");
		String passwd = PropertyUtils.getProperty("login2.password");

		loginPO.typeEmail(email);
		loginPO.typePassword(passwd);
		loginPO.clickSignIn();
		waitUtils.staticWait(STATIC_WAIT * 2);

		accountPO.tapOnMyProfile();

		MyProfilePO myProfilePO = new MyProfilePO(driver);
		myProfilePO.tapOnCreateProfile();

		BasicInformationPO informationPO = new BasicInformationPO(driver);
		informationPO.typeName(PropertyUtils.getProperty("profile2.name")); // works
		informationPO.typePhoneNumber(PropertyUtils.getProperty("profile2.phone")); // works
		informationPO.tapOnGender(PropertyUtils.getProperty("profile2.gender")); // works
		waitUtils.staticWait(STATIC_WAIT * 2);
		informationPO.scrollDown();
		informationPO.setDateOfBirth(PropertyUtils.getProperty("profile2.birth.date")); // works
		waitUtils.staticWait(STATIC_WAIT * 2);
		informationPO.setLivingInLocation(PropertyUtils.getProperty("profile2.living.in"));
		waitUtils.staticWait(STATIC_WAIT * 2);
		informationPO.setHigherEducationLevel(PropertyUtils.getProperty("profile2.highest.education.level"));
		waitUtils.staticWait(STATIC_WAIT * 2);

		informationPO.setCurrentJob(PropertyUtils.getProperty("profile2.current.job"));
		waitUtils.staticWait(STATIC_WAIT * 2);
//		((AndroidDriver) driver).findElementByAndroidUIAutomator("text(\"Engineer\")").click();
		informationPO.setTotalYrsOfWorkExp(PropertyUtils.getProperty("profile2.total.years.of.work.exp"));
		waitUtils.staticWait(STATIC_WAIT * 5);
		informationPO.tapOnContinue();
		waitUtils.staticWait(STATIC_WAIT * 3);

	}

}

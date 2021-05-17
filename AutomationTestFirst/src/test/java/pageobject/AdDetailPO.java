package pageobject;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AdDetailPO extends BasePO {

	List<MobileElement> simiarAds;

	public AdDetailPO(AppiumDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@AndroidFindBy(id = "com.bikroy:id/ad_detail_title")
	AndroidElement adTitle;

	@AndroidFindBy(id = "com.bikroy:id/ad_detail_price")
	AndroidElement adPrice;

	@AndroidFindBy(id = "com.bikroy:id/ad_detail_similar_ads_container")
	AndroidElement similarAdsLayout;

	public boolean isAdTitleDisplayed() {
		return adTitle.isDisplayed();
	}

	public boolean isAdPriceDisplayed() {
		return adPrice.isDisplayed();
	}

	public boolean isSimilarAdsDisplayed() {
		return simiarAds.size() > 0;

	}

	public void loadSimilarAds() {
		gotoFooterBanner();
		simiarAds = similarAdsLayout.findElements(By.xpath("./*//android.widget.RelativeLayout[@index=2]"));
	}

	private void gotoFooterBanner() {
		By banner = By.id("com.bikroy:id/ad_detail_dfp_banner_container");
		int waitTime = 5;
		scrollToBannerElement(banner, waitTime);
	}

	public MobileElement getLowestPriceElement() {
		return findLowestPriceAd(simiarAds);
	}

	private MobileElement findLowestPriceAd(List<MobileElement> ads) {
		final String regex_parse_TK = "Tk ([\\d+\\d*?,\\d*?]*)\\s?";
		final Pattern patternTK = Pattern.compile(regex_parse_TK, Pattern.CASE_INSENSITIVE);

		double minPrice = Double.POSITIVE_INFINITY;
		MobileElement lowestPricedElement = null;

		for (MobileElement ad : ads) {
			try {
				String priceText = ad.findElement(By.id("com.bikroy:id/list_item_price")).getText();
				System.out.println(priceText);
				Matcher matcher = patternTK.matcher(priceText);

				if (matcher.find()) {
					double price = parsePriceFromRegexGroup(matcher);
					if (price <= minPrice) {
						minPrice = price;
						lowestPricedElement = ad;
					}
				}
			} catch (Exception e) {
				// e.printStackTrace();
//				throw new NoSuchElementException("Error in findLowestPriceAd()");
			}
		}

		System.out.println("MIN PRICE " + minPrice);
		return lowestPricedElement;
	}

	private double parsePriceFromRegexGroup(Matcher matcher) {
		String matched = matcher.group(1);
		Collection<String> splitted = Arrays.asList(matched.split(",", 0));
		String tk = splitted.stream().collect(Collectors.joining(""));
		return Double.parseDouble(tk);

	}

	public void scrollToBannerElement(final By locator, int waitTime) {

		for (int atteempt = 0; atteempt < waitTime; atteempt++) {
			try {
				WebElement element = driver.findElement(locator);
				scrollDown();
				scrollUp(element.getSize());
				break;
			} catch (Exception e) {
				driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
			}
			scrollDown();
		}
//		waitUtils.waitForElementToBePresent(locator, driver);
	}

}

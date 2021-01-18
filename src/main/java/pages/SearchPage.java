package pages;

import java.time.Duration;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utility.WaitHelpers;

public class SearchPage {

    private AndroidDriver<MobileElement> androidDriver;
    private IOSDriver<MobileElement> iosDriver;
    private WaitHelpers waitHelpers;

    @AndroidFindBy(id = "com.sensynehealth.hospitals:id/search_button")
    public MobileElement searchButton;
    @AndroidFindBy(id = "com.sensynehealth.hospitals:id/search_src_text")
    public MobileElement searchText;
    @AndroidFindBy(id = "com.sensynehealth.hospitals:id/list")
    public MobileElement searchResults;
    @AndroidFindBy(id = "com.sensynehealth.hospitals:id/search_close_btn")
    public MobileElement closeButton;

    public SearchPage(AppiumDriver appiumDriver) {
        if (appiumDriver instanceof IOSDriver) {
            this.iosDriver = (IOSDriver<MobileElement>) appiumDriver;
            PageFactory.initElements(new AppiumFieldDecorator(this.iosDriver, Duration.ofMillis(10000)), this);
        } else {
            this.androidDriver = (AndroidDriver<MobileElement>) appiumDriver;
            PageFactory.initElements(new AppiumFieldDecorator(this.androidDriver,Duration.ofMillis(10000)), this);
        }
        waitHelpers = new WaitHelpers(appiumDriver);
    }


    public void selectSearch() {
        searchButton.click();
    }

    public void searchInputText(String text) throws InterruptedException {
        searchText.sendKeys(text);
    }

    public void selectClose() throws InterruptedException {
        waitHelpers.waitForElementTobeClickable(closeButton);
        closeButton.click();
    }

}
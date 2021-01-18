package utility;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.PressesKey;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.RemoteWebElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static java.time.Duration.ofMillis;


public class HelperMethods {

    private AppiumDriver appiumDriver;


    public HelperMethods(AppiumDriver appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public void navigateTo(String url) {
        this.appiumDriver.navigate().to(url);
    }

    public boolean isSelected(String accessibilityId) {
        MobileElement element = (MobileElement) appiumDriver.findElementByAccessibilityId(accessibilityId);
        return element.isSelected();
    }

    public boolean isElementPresent() {
        MobileElement element = (MobileElement) appiumDriver.findElementByAccessibilityId("SomeAccessibilityID");
        return element.isDisplayed();
    }

    public void performScroll() {
//        System.out.println("Tag name =" + element.getTagName());
        new TouchAction(appiumDriver)
                .press(PointOption.point(540, 1100))
                .waitAction(waitOptions(ofMillis(800)))
                .moveTo(PointOption.point(540, 0))
                .release()
                .perform();
    }

    public void performScroll(MobileElement element)
    {
        if(appiumDriver instanceof AndroidDriver) {
            ((AndroidDriver<MobileElement>)appiumDriver).findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0))." +
                    "scrollIntoView(new UiSelector().textContains(\"" + element.getText() + "\").instance(0))");

        } else {
            Map<String, Object> args = new HashMap<>();

            args.put("element",  element.getId());
            args.put("toVisible", "not an empty string");
            System.out.println(element.getId());
            appiumDriver.executeScript("mobile:scroll", args);
        }

    }

    public void performSwipe(MobileElement element) throws InterruptedException {
        JavascriptExecutor js = appiumDriver;
        Map<String, Object> params = new HashMap<>();
        params.put("direction", "left");
        //params.put("element",  element.getId());
        js.executeScript("mobile: swipe", params);
    }

    public void pressBackButton() throws  InterruptedException{
        if(appiumDriver instanceof AndroidDriver){
            ((PressesKey) appiumDriver).pressKey(new KeyEvent(AndroidKey.BACK));
        } else {
            appiumDriver.navigate().back();
        }
    }

    public void pressEnterButton() {
        if(appiumDriver instanceof AndroidDriver){
            ((PressesKey) appiumDriver).pressKey(new KeyEvent(AndroidKey.ENTER));
        }
    }
    public Properties getConfigProperties() {
        Properties prop = new Properties();
        try {
            FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/test"
                    + "/resources/config.properties");
            prop.load(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }
    public String generateRandomString(int length){
        return RandomStringUtils.randomAlphabetic(length);
    }

}

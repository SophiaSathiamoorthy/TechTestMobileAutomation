package utility;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class WaitHelpers {

    private AppiumDriver driver;
    private WebDriverWait wait;

    public WaitHelpers(AppiumDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 45);
    }

    public void waitForElementVisible(MobileElement element) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void isVisible(MobileElement element) {
        ExpectedConditions.visibilityOf(element);
    }

    public void waitForElementNotVisible(MobileElement element) {
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void waitForElementTobeClickable(MobileElement element) throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitAndAcceptAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public void waitForElementVisibleByText(By element) throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}

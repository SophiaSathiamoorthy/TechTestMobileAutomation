package pages;

import io.appium.java_client.AppiumDriver;

public class LocalDriverContext {

    private static ThreadLocal<AppiumDriver> appiumDriverThreadLocal = new ThreadLocal<>();

    public static AppiumDriver getAppiumDriver() {
        return appiumDriverThreadLocal.get();
    }

    public static void setAppiumDriverThreadLocal(AppiumDriver driverThreadLocal) {
        appiumDriverThreadLocal.set(driverThreadLocal);
    }
}

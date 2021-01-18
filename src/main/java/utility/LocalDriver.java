package utility;

import POJO.ExecutionConfig;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import pages.LocalDriverContext;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.List;
import java.util.Properties;

public class LocalDriver {
    private static AppiumDriverLocalService service;
    private static AppiumServiceBuilder builder;
    private static WebDriverWait wait;


    public static AppiumDriver getDriver(Properties prop, List<ExecutionConfig> executionConfigList) throws MalformedURLException {
        for(ExecutionConfig config : executionConfigList) {
            DesiredCapabilities cap = new DesiredCapabilities();
            if (config.getDevice().equals("Android")) {

                cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
                cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, config.getPlatformVersion());
                cap.setCapability(MobileCapabilityType.UDID, config.getUdid());
                cap.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + "/apk/app-release.apk");
                //C:\\Users\\surek\\Downloads\\boohoo-test-automation-checkout\\boohoo-test-automation-checkout\\Apk\\app-boohoo-debug.apk
                cap.setCapability(MobileCapabilityType.DEVICE_NAME, config.getDeviceName());
                cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
                cap.setCapability(AndroidMobileCapabilityType.SYSTEM_PORT, Integer.parseInt(config.getSystemPort()));
                cap.setCapability("autoGrantPermissions", true);
                cap.setCapability("gpsEnabled", true);

                /* Start Appium Server from the code
                buildAppiumService(cap, config.getPort());
                if (checkIfServerIsRunnning(4723)) {
                    stopServer();
                }
                startServer();
                 */
                LocalDriverContext.setAppiumDriverThreadLocal(new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap));
                wait = new WebDriverWait(LocalDriverContext.getAppiumDriver(), 10);
                config.setApp((String) cap.getCapability(MobileCapabilityType.APP));


            } else if (config.getDevice().equals("iphone")) {
                cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
                cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, config.getPlatformVersion());
                cap.setCapability(MobileCapabilityType.APP, "/Users/sophiashelton/Library/Developer/Xcode/DerivedData/WebDriverAgent-alwvnomvwrdtzoaxbbkniqrpcdpp/");
                cap.setCapability(MobileCapabilityType.DEVICE_NAME, config.getDeviceName());
                cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
                cap.setCapability(IOSMobileCapabilityType.WDA_LOCAL_PORT,8100);
                cap.setCapability("autoAcceptAlerts", true);
                cap.setCapability("gpsEnabled", true);
                cap.setCapability("autoGrantPermissions", "true");
//                buildAppiumService(cap, config.getPort());
//                if (checkIfServerIsRunnning(4723)) {
//                    stopServer();
//                }
//                startServer();
                LocalDriverContext.setAppiumDriverThreadLocal(new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap));
                wait = new WebDriverWait(LocalDriverContext.getAppiumDriver(), 10);
//                driver = new IOSDriver<>(service.getUrl(), cap);
                config.setApp((String) cap.getCapability(MobileCapabilityType.APP));
            }
        }

        return LocalDriverContext.getAppiumDriver();
    }

    public static void buildAppiumService(DesiredCapabilities capabilities, String port) {
        //Build the Appium service
        service = new AppiumServiceBuilder().usingPort(Integer.valueOf(port)).withCapabilities(capabilities).build();
    }

    private static boolean checkIfServerIsRunnning(int port) {

        boolean isServerRunning = false;
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
            serverSocket.close();
        } catch (IOException e) {
            //If control comes here, then it means that the port is in use
            isServerRunning = true;
        } finally {
            serverSocket = null;
        }
        return isServerRunning;
    }

    public static void startServer() {
        try {
            service.start();
            System.out.println("Appium started " + service.getUrl());
        } catch (Exception e) {
            System.out.println("start server failure");
        }

    }

    public static void stopServer() {
        if (service.isRunning()) {
            service.stop();
        }
    }
}

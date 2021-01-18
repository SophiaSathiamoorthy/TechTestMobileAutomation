package utility;

import POJO.ExecutionConfig;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.google.common.io.Files;
import io.appium.java_client.AppiumDriver;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import pages.SearchPage;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.AfterClass;

import stepdefinitions.SearchSteps;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Hook {
    private AppiumDriver appiumDriver;
    private static WaitHelpers helpers;
    private static HelperMethods helperMethods;
    private static SearchPage searchPage;

    public void setEnvironmentConfiguration(String deviceName, String platformVersion, String udid, String systemPort, String iOSorAndroid, String wdaLocalPort) throws Exception {
        Properties prop = new Properties();
        try {
            FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "/src/test"
                    + "/resources/config.properties");
            prop.load(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<ExecutionConfig> executionConfigList = new ArrayList<>();
        ExecutionConfig executionConfig = new ExecutionConfig();
        ExecutionConfig.setLocalOrRemote(prop.getProperty("localRemote"));
        executionConfig.setIosOrAndroid(iOSorAndroid);
        executionConfig.setDevice(iOSorAndroid);
        executionConfig.setPlatformVersion(platformVersion);
        executionConfig.setDeviceName(deviceName);
        executionConfig.setUdid(udid);
        executionConfig.setSystemPort(systemPort);
        executionConfig.setWdaLocalPort(wdaLocalPort);

        executionConfigList.add(executionConfig);


        setAppiumDriver(prop, executionConfigList);
    }


    private void setAppiumDriver(Properties prop, List<ExecutionConfig> executionConfigList) throws Exception {
        appiumDriver = LocalDriver.getDriver(prop, executionConfigList);
        searchPage = new SearchPage(appiumDriver);
        helpers = new WaitHelpers(appiumDriver);
        helperMethods = new HelperMethods(appiumDriver);
    }

    @After
    public void afterStep(Scenario scenario) throws IOException {
        if (scenario.isFailed()) {
            System.out.println("step failed");

            File scr = ((TakesScreenshot) appiumDriver).getScreenshotAs(OutputType.FILE);
            Files.copy(scr, new File(System.getProperty("user.dir") + "/test-output/HtmlReport/" + scenario.getName() + "_screenshot.png"));
            ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(System.getProperty("user.dir") + "/test-output/HtmlReport/" + scenario.getName() + "_screenshot.png");

            byte[] screenshot = ((TakesScreenshot) appiumDriver).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, System.getProperty("user.dir") + "/test-output/HtmlReport/" + scenario.getName() + "_screenshot.png");

            appiumDriver.resetApp();
        }
    }

}

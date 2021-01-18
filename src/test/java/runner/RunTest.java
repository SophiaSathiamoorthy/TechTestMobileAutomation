package runner;

import POJO.ExecutionConfig;
import com.aventstack.extentreports.service.ExtentService;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import pages.LocalDriverContext;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import utility.Hook;

//@RunWith(Cucumber.class)
@CucumberOptions(features={"src/test/java/features"}
        ,glue={"stepdefinitions","utility"}
        ,plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
        , tags ={"@search"}
)
public class RunTest extends AbstractTestNGCucumberTests{

    @Parameters({"deviceName", "platformVersion","udid", "systemPort", "IOSorAndroid", "wdaLocalPort"})
    @BeforeClass
    public void setup(String deviceName,String platformVersion,String udid, String systemPort, String iOSorAndroid, @Optional String wdaLocalPort) throws Exception {
        Hook hook = new Hook();
        hook.setEnvironmentConfiguration(deviceName, platformVersion, udid, systemPort, iOSorAndroid, wdaLocalPort);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        ExtentService.getInstance().setSystemInfo("OS", ExecutionConfig.getIosOrAndroid());
        System.out.println("tear down");
    }

}

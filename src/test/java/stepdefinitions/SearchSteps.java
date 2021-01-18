package stepdefinitions;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import pages.LocalDriverContext;
import pages.SearchPage;
import utility.HelperMethods;
import utility.WaitHelpers;

import java.util.List;

public class SearchSteps {
    private AppiumDriver appiumDriver;
    private WaitHelpers waitHelpers;
    private HelperMethods helperMethods;
    private SearchPage searchPage;

	 public SearchSteps() {
	        this.appiumDriver = LocalDriverContext.getAppiumDriver();
	    	searchPage = new SearchPage(appiumDriver);
		 waitHelpers = new WaitHelpers(appiumDriver);
	        helperMethods = new HelperMethods(appiumDriver);
	    }

	@Given("I am on the search page")
	public void i_am_on_the_search_page() throws InterruptedException {
		searchPage.selectSearch();
	}

	@When("I search for {string}")
	public void i_search_for(String searchterm) throws InterruptedException {
		searchPage.searchInputText(searchterm);
	}

	@Then("I shall see the hospitals list{string}")
	public void i_shall_see_the_hospitals_list(String expectedHospitalNames) throws InterruptedException {

		waitHelpers.waitForElementVisible(searchPage.searchResults);
		List list1=appiumDriver.findElements(By.xpath("//android.widget.TextView[@resource-id='com.sensynehealth.hospitals:id/hospitalName']"));
		System.out.println("List number" + list1.size());
		int i = 0;
		while(i < list1.size()) {
			String ActualHospitalName = ((MobileElement) list1.get(i)).getText();
			System.out.println("ActuallHospitalName ==> " + ActualHospitalName);
			Assert.assertTrue(expectedHospitalNames.contains(ActualHospitalName));
			i++;
		}
	}

	@Then("I shall see no results")
	public void i_shall_see_no_results() {
		List list1=appiumDriver.findElements(By.xpath("//android.widget.TextView[@resource-id='com.sensynehealth.hospitals:id/hospitalName']"));
		System.out.println("List number" + list1.size());
		Assert.assertTrue(list1.size() == 0);
	}

	@Then("I close the search")
	public void i_close_the_search() throws InterruptedException {
		searchPage.selectClose();
	}

}
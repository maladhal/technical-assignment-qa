package steps;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import poms.Google;
import poms.Gumtree;

public class GoogleSearchTest {
	private WebDriver driver;
	protected static List<WebElement> gumLinks = null;
	
	@Before
	public void initializeTest(){
		driver = new ChromeDriver();
	}
	@After
	public void embedScreenshot() {
	    driver.close();
	}
	
	@Given("^I Open the following URL \"([^\"]*)\"$")
	public void i_Open_the_following_URL(String arg1) throws Throwable {
		try {
			//inherited function to open start page (present in all pages)
			Google.openBaseUrl(driver, arg1);
		}
		catch(Exception e){
			Assert.fail(e.toString());
		}
	}
	
	@Given("^I search for \"([^\"]*)\"$")
	public void i_search_for(String arg1) throws Throwable {
		try {
			Google.getSearchBox(driver).sendKeys(arg1);
			Google.getSearchButton(driver).click();
		}
		catch(Exception e){
			Assert.fail(e.toString());
		}
	}

	@When("^the results return \"([^\"]*)\"$")
	public void the_results_return(String arg1) throws Throwable {
		try {
			if(checkListForText(Google.getAllSearchLinks(driver)).size() == 0){
				throw new Exception("No Gumtree links found");
			}
		}
		catch(Exception e){
			Assert.fail(e.toString());
		}
	}

	@Then("^all gumtree results are valid$")
	public void all_gumtree_results_are_valid_and_great_than_found() throws Throwable {
		try {
			for (int i : checkListForText(Google.getAllSearchLinks(driver))) {
				Google.getAllSearchLinks(driver).get(i).click();
				
				//This step will fail if Gumtree thinks your IP is a bot as it does mine!
				Gumtree.getGumtreeLogo(driver);
				driver.navigate().back();
			}
		}
		catch(Exception e){
			//This step will fail if Gumtree thinks your IP is a bot as it does mine!
			Assert.fail(e.toString() + "This will fail if gumtree thinks your IP is a bot");
		}
	}

	private List<Integer> checkListForText(List<WebElement> list){
		int i = 0;
		List<Integer> l = new ArrayList<Integer>();
	
		for (WebElement e : list) {
			if (e.getText().contains("gumtree")) {
				l.add(i);
			}
			i++;
		}
		Assert.assertTrue(!l.isEmpty(), "No gumtree links found");
		return l;
	}
}

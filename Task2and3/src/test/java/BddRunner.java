import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;


@CucumberOptions(
		features = "src/test/resources/features", glue = { "poms", "steps" }, tags = { "@Task2" }, plugin = { "pretty",
				"html:results", "json:target/cucumber-reports/json-reports/CucumberTestReport.json",
				"rerun:target/cucumber-reports/rerun-reports/rerun.txt" })
public class BddRunner extends AbstractTestNGCucumberTests{
	
}
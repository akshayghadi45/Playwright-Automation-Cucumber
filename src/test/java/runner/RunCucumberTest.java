package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "step_definitions",
        //tags = "@regression"
        tags="@smoke and not @ignore",
        plugin = {"pretty","json:target/cucumber.json", "html:target/cucumberReport.html"}
)
public class RunCucumberTest extends AbstractTestNGCucumberTests {
}

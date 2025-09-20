package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.TestNG;
import org.testng.annotations.DataProvider;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.PhantomReference;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Properties;
import java.util.logging.Level;

import static browser.BrowserManager.logger;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "step_definitions",
        //tags = "@regression"
        tags="@regression and not @ignore",
        plugin = {"pretty","json:target/cucumber.json", "html:target/cucumberReport.html"}
)
public class RunCucumberTest extends AbstractTestNGCucumberTests {
    private static final Properties properties = new Properties();

    static {
        Path configPath = Paths.get(System.getProperty("config.path",
                Paths.get(System.getProperty("user.dir"),
                        "src","main", "resources","config.properties").toString()));
        try(InputStream input = Files.newInputStream(configPath)){
            properties.load(input);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to load the properties file");
        }
    }

    public static void main(String[] args) {
        //Create an instance of testNg
        TestNG testNG = new TestNG();

        //Create a new testNg suite
        XmlSuite suite = new XmlSuite();

        //get the thread count from the properties file
        int threadCount = getThreadCount();
        System.out.println("ThreadCount: " + threadCount);

        //set the number of threads for data provider
        suite.setDataProviderThreadCount(threadCount);

        //create a new testNg test and add it to the suite
        XmlTest test = new XmlTest(suite);
        test.setName("Cucumber Tests");
        test.setXmlClasses(Collections.singletonList(new XmlClass(RunCucumberTest.class)));

        //Disable default listeners (will disable TestNg reports from being generated
        testNG.setUseDefaultListeners(false);

        //Add suite to the testNg instance
        testNG.setXmlSuites(Collections.singletonList(suite));

        // run testNG with the configured suite
        testNG.run();
    }

    //method to get the thread count from config file
    private static int getThreadCount() {
        return Integer.parseInt( properties.getProperty("thread.count", "1"));
    }

    //DataProviderMethod
    //Used for parallel execution, allowing multiple test cases to run simultaneously
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios(); ///Provide data for  the tests, enabling parallel execution
    }
}

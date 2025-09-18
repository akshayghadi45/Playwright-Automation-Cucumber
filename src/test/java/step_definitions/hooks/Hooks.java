package step_definitions.hooks;

import browser.BrowserManager;
import io.cucumber.java.*;

public class Hooks {
    private final BrowserManager browserManager;
    public Hooks(BrowserManager browserManager) {
        this.browserManager = browserManager;
    }

    //runs once before all the test
    @BeforeAll
    public static void beforeAll(){
        System.out.println("\nExecuting test suite");
    }

    //runs once after all the test
    @AfterAll
    public static void afterAll(){
        System.out.println("\nTest suite execution completed");
    }
    //runs once before each test
    @Before
    public void setup(){
        browserManager.setUp();
    }

    //runs after each test
    @After
    public void tearDown(Scenario scenario){
        if(scenario.isFailed()){
            byte[] screenshot = browserManager.takeScreenshot();
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
        browserManager.tearDown();
    }
}

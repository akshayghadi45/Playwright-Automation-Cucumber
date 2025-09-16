package step_definitions.hooks;

import browser.BrowserManager;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;

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
    public void tearDown(){
        browserManager.tearDown();
    }
}

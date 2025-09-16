package step_definitions;

import browser.BrowserManager;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import java.awt.*;

public class Homepage_Steps {

//    private final Page page;
//    private final BrowserContext browserContext;
//    private Page mostRecentPage;

    //Constructor
//    public Homepage_Steps() {
//        //get viewport size of screen
//        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        int width = (int) screenSize.getWidth();
//        int height = (int) screenSize.getHeight();
//
//        Playwright playwright = Playwright.create();
//        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
//        this.browserContext = browser.newContext(new Browser.NewContextOptions().setViewportSize(width, height));
//        this.page = browserContext.newPage();
//    }

    BrowserManager browserManager;

    public  Homepage_Steps(BrowserManager browserManager){
        this.browserManager=browserManager;
    }

    @Given("I navigate to webdriveruniversity homepage")
    public void i_navigate_to_webdriveruniversity_homepage() {
        browserManager.page.navigate("https://www.webdriveruniversity.com");
    }
    @When("I click on the contact us button")
    public void i_click_on_the_contact_us_button() {
        browserManager.page = browserManager.browserContext.waitForPage(()->{
                browserManager.page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("CONTACT US Contact Us Form")).click(); // Missing semicolon
        });
        browserManager.page.bringToFront();

    }

}

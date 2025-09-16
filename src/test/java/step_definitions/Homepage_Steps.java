package step_definitions;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.awt.*;

public class Homepage_Steps {

    private final Page page;
    private final BrowserContext browserContext;
    private Page mostRecentPage;

    //Constructor
    public Homepage_Steps() {
        //get viewport size of screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        this.browserContext = browser.newContext(new Browser.NewContextOptions().setViewportSize(width, height));
        this.page = browserContext.newPage();
    }

    @Given("I navigate to webdriveruniversity homepage")
    public void i_navigate_to_webdriveruniversity_homepage() {
        page.navigate("https://www.webdriveruniversity.com");
    }
    @When("I click on the contact us button")
    public void i_click_on_the_contact_us_button() {
        mostRecentPage = browserContext.waitForPage(()->{
                page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("CONTACT US Contact Us Form")).click(); // Missing semicolon
        });
        mostRecentPage.bringToFront();
        mostRecentPage.pause();

    }

}

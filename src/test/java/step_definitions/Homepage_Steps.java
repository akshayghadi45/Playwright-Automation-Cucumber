package step_definitions;

import browser.BrowserManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pages.HomePage;
import pages.base.BasePage;

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
    BasePage basePage;
    HomePage homePage;

    public  Homepage_Steps(BrowserManager browserManager,  BasePage basePage, HomePage homePage) {
        this.browserManager=browserManager;
        this.basePage=basePage;
        this.homePage=homePage;
    }

    @Given("I navigate to webdriveruniversity homepage")
    public void i_navigate_to_webdriveruniversity_homepage() {
       //basePage.navigate("https://www.webdriveruniversity.com");
        homePage.NavigateToHomePage();
    }
    @When("I click on the contact us button")
    public void i_click_on_the_contact_us_button() {

//        browserManager.setPage( browserManager.getBrowserContext().waitForPage(()->{
//            //browserManager.getPage().getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("CONTACT US Contact Us Form")).click();
//            //Commented above code to make use of the base class
//            basePage.waitAndClickByRole("LINK","CONTACT US Contact Us Form");
//        }));
//        browserManager.getPage().bringToFront();
        homePage.clickContactUsButton();

    }
    @When("I click on the login portal button")
    public void i_click_on_the_login_portal_button() {
//        browserManager.setPage( browserManager.getBrowserContext().waitForPage(()->{
//            //browserManager.getPage().locator("#login-portal").click();
//            basePage.waitAndClickByRole("LINK","LOGIN PORTAL Login Portal");
//        }));
//        browserManager.getPage().bringToFront();
        homePage.clickLoginButton();
    }

}

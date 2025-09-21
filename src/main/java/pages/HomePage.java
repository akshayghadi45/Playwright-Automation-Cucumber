package pages;

import browser.BrowserManager;
import pages.base.BasePage;

public class HomePage extends BasePage {

    public HomePage(BrowserManager browserManager) {
        super(browserManager);
    }
    public void NavigateToHomePage() {
        navigate("https://www.webdriveruniversity.com");
    }

    public void clickContactUsButton() {
        getBrowserManager().setPage( getBrowserManager().getBrowserContext().waitForPage(()->{
            //browserManager.getPage().getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("CONTACT US Contact Us Form")).click();
            //Commented above code to make use of the base class
            waitAndClickByRole("LINK","CONTACT US Contact Us Form");
        }));
        getBrowserManager().getPage().bringToFront();
    }

    public void clickLoginButton() {
        getBrowserManager().setPage( getBrowserManager().getBrowserContext().waitForPage(()->{
            //browserManager.getPage().locator("#login-portal").click();
            waitAndClickByRole("LINK","LOGIN PORTAL Login Portal");
        }));
        getBrowserManager().getPage().bringToFront();
    }
}

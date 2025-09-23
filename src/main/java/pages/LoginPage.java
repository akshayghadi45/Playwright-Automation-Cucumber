package pages;

import browser.BrowserManager;
import com.microsoft.playwright.Locator;
import org.testng.Assert;
import pages.base.BasePage;

public class LoginPage extends BasePage {
    String alertText;
    public LoginPage(BrowserManager browserManager) {
        super(browserManager);
    }

    public void typeLoginIdAndPassword(String id, String password) {
//        getBrowserManager().getPage().getByPlaceholder("Username").fill(id);
//        getBrowserManager().getPage().getByPlaceholder("Password").fill(password);
        fillField("Username", id);
        fillField("Password", password);
    }

    public void clickLoginButton() {

        getBrowserManager().getPage().onceDialog(dialog -> {
            alertText = dialog.message();
            dialog.accept();
        });
        Locator loginButton = getBrowserManager().getPage().locator("#login-button");
        loginButton.hover();
        loginButton.click( new Locator.ClickOptions().setForce(true)); /// force command will click the button no matter what
    }

    public void validataValidationMessage(String message) {
        Assert.assertEquals(alertText, message);
    }
}

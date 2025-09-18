package step_definitions;

import browser.BrowserManager;
import com.microsoft.playwright.Locator;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class Login_Steps {
    BrowserManager browserManager;
    String alertText;

    public  Login_Steps(BrowserManager browserManager) {
        this.browserManager = browserManager;
    }

    @And("I give login id {string} and password {string}")
    public void i_give_login_id_and_password(String id, String password) {
        browserManager.page.getByPlaceholder("Username").fill(id);
        browserManager.page.getByPlaceholder("Password").fill(password);
        //browserManager.page.pause();
    }

    @And("I click on the login button")
    public void i_click_on_the_login_button() {
        browserManager.page.onceDialog(dialog -> {
            alertText = dialog.message();
            dialog.accept();
        });
        Locator loginButton = browserManager.page.locator("#login-button");
        loginButton.hover();
        loginButton.click( new Locator.ClickOptions().setForce(true)); /// force command will click the button no matter what
    }

    @Then("I should see a validation message {string}")
    public void i_should_see_a_validation_message(String expectedAlertMessage) {
        Assert.assertEquals(alertText, expectedAlertMessage);
    }
}

package step_definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pages.LoginPage;

public class Login_Steps {
    LoginPage loginPage;

    public  Login_Steps( LoginPage loginPage) {
        this.loginPage = loginPage;
    }

    @And("I give login id {string} and password {string}")
    public void i_give_login_id_and_password(String id, String password) {
//        browserManager.getPage().getByPlaceholder("Username").fill(id);
//        browserManager.getPage().getByPlaceholder("Password").fill(password);
        loginPage.typeLoginIdAndPassword(id, password);
    }

    @And("I click on the login button")
    public void i_click_on_the_login_button() {
//        browserManager.getPage().onceDialog(dialog -> {
//            alertText = dialog.message();
//            dialog.accept();
//        });
//        Locator loginButton = browserManager.getPage().locator("#login-button");
//        loginButton.hover();
//        loginButton.click( new Locator.ClickOptions().setForce(true)); /// force command will click the button no matter what
        loginPage.clickLoginButton();
    }

    @Then("I should see a validation message {string}")
    public void i_should_see_a_validation_message(String expectedAlertMessage) {
        //Assert.assertEquals(alertText, expectedAlertMessage);
        loginPage.validataValidationMessage(expectedAlertMessage);

    }


}

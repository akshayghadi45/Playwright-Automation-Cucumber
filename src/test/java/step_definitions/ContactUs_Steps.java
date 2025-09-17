package step_definitions;

import browser.BrowserManager;
import com.microsoft.playwright.Locator;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ContactUs_Steps {
    BrowserManager browserManager;
    public ContactUs_Steps(BrowserManager browserManager){
        this.browserManager=browserManager;
    }

    @And("I type a first name")
    public void i_type_a_first_name() {
        browserManager.page.getByPlaceholder("First Name").fill("Akshay");
    }

    @And("I type a last name")
    public void i_type_a_last_name() {
        browserManager.page.getByPlaceholder("Last Name").fill("Ghadi");
    }

    @And("I enter email address")
    public void i_enter_email_address() {
        browserManager.page.getByPlaceholder("Email Address").fill("akshay.ghadi@gmail.com");
    }

    @And("I type a comment")
    public void i_type_a_comment() {
        browserManager.page.getByPlaceholder("Comments").fill("SOmehtinghjb hsdbci");
    }

    @And("I click on the submit button")
    public void i_click_on_the_submit_button() {
        browserManager.page.locator("input[value=SUBMIT]").click();
    }

    @Then("I should be presented with a successful contact us submission message")
    public void i_should_be_presented_with_a_successful_contact_us_submission_message() {
        assertThat(browserManager.page.locator("#contact_reply > h1")).
                hasText("Thank You for your Message!");

    }
    @Then("I should be presented with a unsuccessful contact us submission message")
    public void i_should_be_presented_with_a_unsuccessful_contact_us_submission_message() {
        //wait for the body element
        browserManager.page.waitForSelector("body");

        //Locator of the body element
        Locator bodyElement = browserManager.page.locator("body");
        //Extract text from the element
        String bodyText =  bodyElement.textContent();

        //Assert that the body text matches the expected pattern
        Pattern pattern = Pattern.compile("Error: (all fields are required|Invalid email address)");
        Matcher matcher = pattern.matcher(bodyText);
        Assert.assertTrue(matcher.find(), "Body text does not match the expected pattern: "+ bodyText);



        //Alternate way
//        assertThat(browserManager.page.locator("//body")).
//                containsText("Error: Invalid email address");
//        assertThat(browserManager.page.locator("//body")).
//                containsText("Error: all fields are required");
    }

    @And("I type a specific first name {string}")
    public void i_type_a_specific_first_name(String firstName) {
        browserManager.page.getByPlaceholder("First Name").fill(firstName);
    }

    @And("I type a specific last name {string}")
    public void i_type_a_specific_last_name(String lastName) {
        browserManager.page.getByPlaceholder("Last Name").fill(lastName);
    }

    @And("I enter a specific email address {string}")
    public void i_enter_a_specific_email_address(String emailAddress) {
        browserManager.page.getByPlaceholder("Email Address").fill(emailAddress);
    }

    @And("I type a specific comment {string}")
    public void i_type_a_specific_comment(String string) {
        browserManager.page.getByPlaceholder("Comments").fill(string);
    }



}

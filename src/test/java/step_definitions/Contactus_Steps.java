package step_definitions;

import browser.BrowserManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class Contactus_Steps {
    BrowserManager browserManager;
    public  Contactus_Steps(BrowserManager browserManager){
        this.browserManager=browserManager;
    }

    @And("I type a first name")
    public void i_type_a_first_name() {
        browserManager.page.getByPlaceholder("First Name").fill("Akshay");
        browserManager.page.pause();
        System.out.println("To do 3");

    }
    @And("I type a last name")
    public void i_type_a_last_name() {
        System.out.println("To do 4");
    }
    @And("I enter email address")
    public void i_enter_email_address() {
        System.out.println("To do 5");
    }
    @And("I type a comment")
    public void i_type_a_comment() {
        System.out.println("To do 6");
    }
    @And("I click on the submit button")
    public void i_click_on_the_submit_button() {
        System.out.println("To do 7");
    }
    @Then("I should be presented with a successful contact us submission message")
    public void i_should_be_presented_with_a_successful_contact_us_submission_message() {
        System.out.println("To do 8");

    }
}

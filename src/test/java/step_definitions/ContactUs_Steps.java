package step_definitions;

import browser.BrowserManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import context.PersonContext;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import net.datafaker.Faker;
import org.testng.Assert;
import pages.ContactUsPage;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.testng.Assert.assertTrue;



public class ContactUs_Steps {
    BrowserManager browserManager;
    private final Faker faker = new Faker();
    private final PersonContext personContext;
    ContactUsPage contactUsPage;

    public ContactUs_Steps(BrowserManager browserManager, PersonContext personContext, ContactUsPage contactUsPage) {
        this.browserManager=browserManager;
        this.personContext = personContext;
        this.contactUsPage = contactUsPage;
    }

    @And("I type a first name")
    public void i_type_a_first_name() {
        //browserManager.getPage().getByPlaceholder("First Name").fill("Akshay");
        contactUsPage.typeFirstName("Akshay");


    }

    @And("I type a last name")
    public void i_type_a_last_name() {
        //browserManager.getPage().getByPlaceholder("Last Name").fill("Ghadi");
        contactUsPage.typeLastName("David");
    }

    @And("I enter email address")
    public void i_enter_email_address() {
        //browserManager.getPage().getByPlaceholder("Email Address").fill("akshay.ghadi@gmail.com");
        contactUsPage.typeEmail("akshay.ghadi@gmail.com");
    }

    @And("I type a comment")
    public void i_type_a_comment() {
        //browserManager.getPage().getByPlaceholder("Comments").fill("SOmehtinghjb hsdbci");
        contactUsPage.typeComment("This is a comment");
    }

    @And("I click on the submit button")
    public void i_click_on_the_submit_button() {
        //browserManager.getPage().locator("input[value=SUBMIT]").click();
        contactUsPage.clickOnSubmitButton();
    }

    @Then("I should be presented with a successful contact us submission message")
    public void i_should_be_presented_with_a_successful_contact_us_submission_message() {
        browserManager.getPage().waitForSelector("#contact_reply > h1", new Page.WaitForSelectorOptions().setTimeout(10000));
        assertThat(browserManager.getPage().locator("#contact_reply > h1")).
                hasText("Thank You for your Message!");

    }
    @Then("I should be presented with a unsuccessful contact us submission message")
    public void i_should_be_presented_with_a_unsuccessful_contact_us_submission_message() {
        //wait for the body element
        browserManager.getPage().waitForSelector("body");

        //Locator of the body element
        Locator bodyElement = browserManager.getPage().locator("body");
        //Extract text from the element
        String bodyText =  bodyElement.textContent();

        //Assert that the body text matches the expected pattern
        Pattern pattern = Pattern.compile("Error: (all fields are required|Invalid email address)");
        Matcher matcher = pattern.matcher(bodyText);
        assertTrue(matcher.find(), "Body text does not match the expected pattern: "+ bodyText);



        //Alternate way
//        assertThat(browserManager.page.locator("//body")).
//                containsText("Error: Invalid email address");
//        assertThat(browserManager.page.locator("//body")).
//                containsText("Error: all fields are required");
    }

    ///************* Specific data passing using variables****************///
    @And("I type a specific first name {string}")
    public void i_type_a_specific_first_name(String firstName) {
        browserManager.getPage().getByPlaceholder("First Name").fill(firstName);
    }

    @And("I type a specific last name {string}")
    public void i_type_a_specific_last_name(String lastName) {
        browserManager.getPage().getByPlaceholder("Last Name").fill(lastName);
    }

    @And("I enter a specific email address {string}")
    public void i_enter_a_specific_email_address(String emailAddress) {
        browserManager.getPage().getByPlaceholder("Email Address").fill(emailAddress);
    }

    @And("I type a specific comment {string}")
    public void i_type_a_specific_comment(String string) {
        browserManager.getPage().getByPlaceholder("Comments").fill(string);
    }

/// Random data generation using DataFaker dependency ///

    @And("I type a random first name")
    public void i_type_a_random_first_name() {
        String randomFirstName = faker.name().firstName();
        personContext.setRandomFirstName(randomFirstName);
        browserManager.getPage().getByPlaceholder("First Name").fill(randomFirstName);
    }

    @And("I type a random last name")
    public void i_type_a_random_last_name() {
        String randomLastName = faker.name().lastName();
        personContext.setRandomLastName(randomLastName);
        browserManager.getPage().getByPlaceholder("Last Name").fill(randomLastName);
    }

    @And("I enter an random email address")
    public void i_enter_an_random_email_address() {
        String randomEmail = faker.internet().emailAddress();
        personContext.setRandomEmail(randomEmail);
        browserManager.getPage().getByPlaceholder("Email Address").fill(randomEmail);
    }

    @And("I add a random comment")
    public void i_add_a_random_comment() {
        browserManager.getPage().getByPlaceholder("Comments").
                fill("Random "+personContext.getRandomFirstName() + " " + personContext.getRandomLastName());
    }

    /// Scenario outlines
    @And("I type a first name {word} and a last name {word}")
    public void i_type_a_first_name_john_and_a_last_name_jones(String firstName, String lastName) {
        browserManager.getPage().getByPlaceholder("First Name").fill(firstName);
        browserManager.getPage().getByPlaceholder("Last Name").fill(lastName);
    }


    @And("I type a email address {string} and I comment {string}")
    public void i_type_a_email_address_and_i_comment_hey_how_are_you(String emailAddress, String comment) {
        browserManager.getPage().getByPlaceholder("Email Address").fill(emailAddress);
        browserManager.getPage().getByPlaceholder("Comments").fill(comment);
    }


    @Then("I should be presented with a header text {string}")
    public void i_should_be_presented_with_a_header_text(String expectedMessage) {
        //Wait for the target element
        browserManager.getPage().waitForSelector("//h1 | //body");

        //get all elements inner text
        List<String> innertexts = browserManager.getPage().locator("//h1 | //body").allInnerTexts();

        //variable to store the found text
        String foundText ="";
        Boolean isFound = false;
        //Check if the text is present in the innerTexts
        for(String innerText: innertexts){
            if(innerText.contains(expectedMessage)){
                foundText=innerText;
                isFound=true;
                break;
            }
            else{
                foundText=innerText;
            }
        }

        //perform assertion
        assertTrue(isFound, "The element does not contain the expected message. Expected message: "+
                expectedMessage + " Actual messsage: "+foundText);



    }


}

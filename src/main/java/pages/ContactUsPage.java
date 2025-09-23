package pages;

import browser.BrowserManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import pages.base.BasePage;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.testng.Assert.assertTrue;

public class ContactUsPage extends BasePage {
    public ContactUsPage(BrowserManager browserManager) {
        super(browserManager);
    }

    public void typeFirstName(String firstName){
        //getBrowserManager().getPage().getByPlaceholder("First Name").fill(firstName);
        fillField("First Name",  firstName);
    }
    public void typeLastName(String lastName){
        //getBrowserManager().getPage().getByPlaceholder("Last Name").fill(lastName);
        fillField("Last Name",  lastName);
    }
    public void typeEmail(String email){
        //getBrowserManager().getPage().getByPlaceholder("Email Address").fill(email);
        fillField("Email Address",  email);
    }

    public void typeComment(String comment){
        //getBrowserManager().getPage().getByPlaceholder("Comments").fill(comment);
        fillField("Comments",  comment);
    }

    public void clickOnSubmitButton(){
        waitAndClickBySelector("input[value=SUBMIT]");
    }

    public  void verifySuccessfulSubmissionMessage(){
        getBrowserManager().getPage().waitForSelector("#contact_reply > h1", new Page.WaitForSelectorOptions().setTimeout(10000));
        assertThat(getBrowserManager().getPage().locator("#contact_reply > h1")).
                hasText("Thank You for your Message!");
    }

    public void verifyUnsuccessfulSubmissionMessage(){
        //wait for the body element
        getBrowserManager().getPage().waitForSelector("body");

        //Locator of the body element
        Locator bodyElement = getBrowserManager().getPage().locator("body");
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

    public void verifyHeaderText(String expectedMessage){
        //get all elements inner text
        List<String> innertexts = getBrowserManager().getPage().locator("//h1 | //body").allInnerTexts();

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

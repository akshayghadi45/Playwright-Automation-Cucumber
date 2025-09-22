package pages;

import browser.BrowserManager;
import pages.base.BasePage;

public class ContactUsPage extends BasePage {
    BrowserManager browserManager;
    public ContactUsPage(BrowserManager browserManager) {
        super(browserManager);
    }

    public void typeFirstName(String firstName){
        getBrowserManager().getPage().getByPlaceholder("First Name").fill(firstName);
    }
    public void typeLastName(String lastName){
        getBrowserManager().getPage().getByPlaceholder("Last Name").fill(lastName);
    }
    public void typeEmail(String email){
        getBrowserManager().getPage().getByPlaceholder("Email Address").fill(email);
    }

    public void typeComment(String comment){
        getBrowserManager().getPage().getByPlaceholder("Comments").fill(comment);
    }

    public void clickOnSubmitButton(){
        waitAndClickBySelector("input[value=SUBMIT]");
    }

}

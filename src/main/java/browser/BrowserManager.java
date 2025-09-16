package browser;

import com.microsoft.playwright.*;

import java.awt.*;

public class BrowserManager {
    Playwright playwright; //used to create an instance of the Chromium, Firefox browser etc.
    Browser browser;//is the isolated browser session.
    BrowserContext browserContext;//represents the browser instance.
    Page page;//is the single tab or window in the browser.

    public void setUp(){

        System.out.println("Setting up playwright");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();

        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        browserContext = browser.newContext(new Browser.NewContextOptions().setViewportSize(width, height));
        page=browserContext.newPage();

        System.out.println("Setup completed");
    }

    public void tearDown(){
        System.out.println("Tearing down playwright");
        if(page!=null) page.close();
        if(browserContext!=null) browserContext.close();
        if(browser!=null)browser.close();
        if(playwright!=null)playwright.close();
        System.out.println("Tear down completed");
    }
}

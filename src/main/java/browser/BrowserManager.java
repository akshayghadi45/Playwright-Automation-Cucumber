package browser;

import com.microsoft.playwright.*;


import java.awt.*;

public class BrowserManager {
    public Playwright playwright; //used to create an instance of the Chromium, Firefox browser etc.
    public Browser browser;//is the isolated browser session.
    public BrowserContext browserContext;//represents the browser instance.
    public Page page;//is the single tab or window in the browser

    //to capture the screenshot of the page
    public byte[] takeScreenshot(){
        if(page!=null){
            return page.screenshot();
        }
        return new byte[0];
    }

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

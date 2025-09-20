package browser;

import com.microsoft.playwright.*;


import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BrowserManager {
    /// commenting the old code for implementation of threading and parallel running of test cases
//    public Playwright playwright; //used to create an instance of the Chromium, Firefox browser etc.
//    public Browser browser;//is the isolated browser session.
//    public BrowserContext browserContext;//represents the browser instance.
//    public Page page;//is the single tab or window in the browser

    private static final ThreadLocal<Playwright> playwright = new ThreadLocal<>();
    private static final ThreadLocal<Browser> browser = new ThreadLocal<>();
    private static final ThreadLocal<BrowserContext> browserContext = new ThreadLocal<>();
    private static final ThreadLocal<Page> page = new ThreadLocal<>();

    public Properties properties;
    public static Logger logger= Logger.getLogger(BrowserManager.class.getName());

    public BrowserManager(){
        properties=new Properties();
        Path configPath = Paths.get(System.getProperty("config.path",
                Paths.get(System.getProperty("user.dir"),
                        "src","main", "resources","config.properties").toString()));
        try(InputStream input = Files.newInputStream(configPath)){
            properties.load(input);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to load the properties file");
        }
    }
    //to capture the screenshot of the page
    public byte[] takeScreenshot(){
        if(page.get()!=null){
            return page.get().screenshot();
        }
        return new byte[0];
    }

    public void setUp(){
        logger.info("Setting up playwright");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        try {
            //Initialize a new instance of playwright and make sure resources are auto closed
            String browserType = properties.getProperty("browser", "chromium");
            playwright.set(Playwright.create());
            switch (browserType) {
                case "chromium": {
                    browser.set( playwright.get().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                    break;
                }
                case "firefox":{
                    browser.set( playwright.get().firefox().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                    break;
                }
                case "webkit":{
                    browser.set(playwright.get().webkit().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                    break;
                    }
                default: {
                    logger.warning("Unknown browser type: "+ browserType +" Defaulting to chromium" );
                    browser.set(playwright.get().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                    break;
                }
            }
            //browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            browserContext.set( browser.get().newContext(new Browser.NewContextOptions().setViewportSize(width, height)));
            page.set(browserContext.get().newPage());

            logger.info("Setup completed");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to setup playwright", e);
        }
    }

    public void setPage(Page newPage){
        page.set(newPage);
    }
    public  Page getPage(){
        return page.get();
    }
    public void setBrowserContext(BrowserContext newBrowserContext){
        browserContext.set(newBrowserContext);
    }
    public  BrowserContext getBrowserContext(){
        return browserContext.get();
    }

    public void tearDown(){
        try {
            System.out.println("Tearing down playwright");
            if (page.get() != null) page.get().close();
            if (browserContext.get() != null) browserContext.get().close();
            if (browser.get() != null) browser.get().close();
            if (playwright.get() != null) playwright.get().close();
            logger.info("Tear down completed");
        }catch (Exception e){
            logger.log(Level.SEVERE, "Failed to tear down playwright", e);
        }
    }
}

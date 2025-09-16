import com.microsoft.playwright.*;

import java.awt.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //get viewport size of screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();

        //Initialize a new instance of playwright and make sure resources are auto closed
        try(Playwright playwright = Playwright.create()) {
            Browser  browser = playwright.chromium().launch( new BrowserType.LaunchOptions().setHeadless(false));
            BrowserContext browserContext = browser.newContext( new Browser.NewContextOptions().setViewportSize(width, height));
            Page page = browserContext.newPage();
            page.navigate("https://www.webdriveruniversity.com");
            System.out.println(page.title());
            page.close();
        }

    }
}
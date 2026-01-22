import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class FirstTest {

    @Test
    public void firstLogin() {

       //open browser

        WebDriver browser = new ChromeDriver();

        //enter the site

        browser.get("https://www.saucedemo.com/");

        //close browser
        // close (window) or quit (browser)

        //browser.quit();


    }




}

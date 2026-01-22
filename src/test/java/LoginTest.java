import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
//import static org.testng.Assert.*;

public class LoginTest {

    //WebDriver browser;
    //@BeforeMethod (в файлах наследующих предусловия писать extends)
    //public void setup() {}


    @Test
    public void correctLogin() {

        //Chrome options (не забыть вписать (options) в ChromeDriver())
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized"); //полноэкранный
        options.addArguments("headless"); //тесты без открытия браузера

       //open browser
        WebDriver browser = new ChromeDriver();

        //enter the site
        browser.get("https://www.saucedemo.com/");

        //username field options
        browser.findElement(By.xpath("//*[@id='user-name']")) .sendKeys("standard_user");
        //.sendKeys(Keys.control + "A")
        //.sendKeys(Keys.Back_Space)

        // Password Field
        browser.findElement(By.xpath("//*[@id='password']")) .sendKeys("secret_sauce");

        //login button (click)
        browser.findElement(By.xpath("//*[@data-test='login-button']")) .click();

        //boolean check
        boolean titleisDisplayed = browser.findElement(By.xpath("//span[@data-test='title']"))
                .isDisplayed();
        assertTrue(titleisDisplayed, "Заголовок не виден");

        //textCheck
        String titleName = browser.findElement(By.xpath("//span[@data-test='title']")) .getText();
        assertEquals(titleName, "Products", "incorrect title name");

        //close browser
        // close (window) or quit (browser)
        browser.quit();
    }

    @Test
    public void incorrectLogin() {

        WebDriver browser = new ChromeDriver();

        browser.get("https://www.saucedemo.com/");

        browser.findElement(By.xpath("//*[@id='user-name']")) .sendKeys("locked_out_user");
        browser.findElement(By.xpath("//*[@id='password']")) .sendKeys("secret_sauce");
        browser.findElement(By.xpath("//*[@data-test='login-button']")) .click();

        boolean errorisDisplayed = browser.findElement(By.xpath("//*[@data-test='error']")) .
                isDisplayed();
        assertTrue(errorisDisplayed, "Нет Ошибки");

        String errorMessage = browser.findElement(By.xpath("//*[@data-test='error']")) .getText();
        assertEquals(errorMessage, "Epic sadface: Sorry, this user has been locked out.",
                "incorrect error text");

        browser.quit();
    }

    //@AfterMethod
    //public void testending() {}
}

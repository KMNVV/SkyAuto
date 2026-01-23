package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private final By loginInput = By.xpath("//*[@id='user-name']");
    private final By passwordInput = By.xpath("//*[@id='password']");
    private final By loginButton = By.xpath("//*[@data-test='login-button']");
    private final By errorMsg = By.xpath("//*[@data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(BASE_URL);
    }

    public void login(String user, String password) {
        driver.findElement(loginInput) .sendKeys(user);
        driver.findElement(passwordInput) .sendKeys(password);
        driver.findElement(loginButton) .click();
    }
    public boolean isErrorDisplayed() {
        return driver.findElement(errorMsg).isDisplayed();
    }
    public String getErrorText() {
        return driver.findElement(errorMsg).getText();
    }

}

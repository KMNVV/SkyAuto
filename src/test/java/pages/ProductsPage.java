package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage {
    WebDriver driver;
    private final By title = By.xpath("//span[@data-test='title']");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isTitleDisplayed() {
        return driver.findElement(title).isDisplayed();
    }

    public String isTextDisplayed() {
        return driver.findElement(title).getText();
    }
}

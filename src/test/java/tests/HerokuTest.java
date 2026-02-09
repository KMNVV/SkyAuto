package tests;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class HerokuTest extends BaseTest {

    private final String PROFILE_PATTERN = "//*[text()='name: user%s']/ancestor::div[@class='figure']";

    @Test
    public void frameCheck() {
        driver.get("http://the-internet.herokuapp.com/iframe");
        driver.switchTo().frame("mce_0_ifr");
        String text = driver.findElement(By.tagName("p")).getText();
        assertEquals(text, "ggg");
        driver.switchTo().defaultContent();
    }

    @Test
    public void alertCheck() throws InterruptedException {
        driver.get("http://the-internet.herokuapp.com/context_menu");
        Actions action = new Actions(driver);
        action.contextClick(driver.findElement(By.id("hot-spot"))).perform();
        Thread.sleep(3000);
        Alert alert = driver.switchTo().alert();
        String text = alert.getText();
        System.out.println(text);
        alert.accept();
    }

    @Test
    public void hoverCheck() {
        driver.get("http://the-internet.herokuapp.com/hovers");
        hoverProfile(2);
        hoverProfile(1);
    }

    public void hoverProfile(int user) {
        By profile = By.xpath(PROFILE_PATTERN.formatted(user));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(profile)).perform();
    }
}
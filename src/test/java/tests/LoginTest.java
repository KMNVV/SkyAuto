package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class LoginTest extends BaseTest{

    @Test
    public void correctLogin() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        assertTrue(productsPage.isTitleDisplayed(), "Заголовок не виден");
        assertEquals(productsPage.isTextDisplayed(), "Products", "incorrect " +
                "title name");

        driver.quit();
    }

    @Test
    public void incorrectLogin() {
        loginPage.open();
        loginPage.login("locked_out_user", "secret_sauce");

        assertTrue(loginPage.isErrorDisplayed(), "Нет сообщения об Ошибке");
        assertEquals(loginPage.getErrorText(), "Epic sadface: Sorry, this user " +
                        "has been locked out.",
                "incorrect error text");

        driver.quit();
    }

    @Test
    public void emptyLogin() {
        loginPage.open();
        loginPage.login("", "secret_sauce");

        assertTrue(loginPage.isErrorDisplayed(), "Нет сообщения об Ошибке");
        assertEquals(loginPage.getErrorText(), "Epic sadface: Username is required",
                "incorrect error text");

        driver.quit();
    }

    @Test
    public void emptyPassword() {
        loginPage.open();
        loginPage.login("standard_user", "");

        assertTrue(loginPage.isErrorDisplayed(), "Нет сообщения об Ошибке");
        assertEquals(loginPage.getErrorText(), "Epic sadface: Password is required",
                "incorrect error text");

        driver.quit();
    }

    @Test
    public void upperCaseLogin() {
        loginPage.open();
        loginPage.login("Standard_user", "secret_sauce");

        assertTrue(loginPage.isErrorDisplayed(), "Нет сообщения об Ошибке");
        assertEquals(loginPage.getErrorText(), "Epic sadface: Username and password " +
                        "do not match any user in this service",
                "incorrect error text");

        driver.quit();
    }


}


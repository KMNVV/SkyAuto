package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static enums.TitleNaming.PRODUCTS;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static user.UserFactory.withAdminPermission;

public class LoginTest extends BaseTest {

    @Test
    public void correctLogin() {
        loginPage.open();
        loginPage.login(withAdminPermission());

        assertTrue(productsPage.isTitleDisplayed(), "Заголовок не виден");
        assertEquals(productsPage.isTextDisplayed(), PRODUCTS.getDisplayName(), "incorrect title name");
    }

    @DataProvider(name = "negativeCases")
    public Object[][] loginData() {
        return new Object[][]{
                {"locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out."},
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"standard_user", "", "Epic sadface: Password is required"},
                {"Standard_user", "secret_sauce", "Epic sadface: Username and password do not match any user " +
                        "in this service"},
        };
    }

    @Epic("Тестирование интернет площадки")
    @Feature("Проверка расчета скидки")
    @Test(dataProvider = "negativeCases")
    public void incorrectLogin(String user, String password, String errorMsg) {
        loginPage.open();
        loginPage.login(user, password);

        assertTrue(loginPage.isErrorDisplayed(), "Нет сообщения об Ошибке");
        assertEquals(loginPage.getErrorText(), errorMsg,
                "incorrect error text");
    }
}
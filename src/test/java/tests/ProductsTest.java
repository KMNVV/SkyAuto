package tests;

import org.testng.annotations.Test;
import user.UserFactory;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;
import static user.UserFactory.withAdminPermission;

public class ProductsTest extends BaseTest {
    List<String> goodsList = new ArrayList<>(
            List.of("Sauce Labs Onesie", "Sauce Labs Fleece Jacket", "Test.allTheThings() T-Shirt (Red)")
    );

    @Test
    public void checkGoodsAdded() {
        loginPage.open();
        loginPage.login(withAdminPermission());
        assertTrue(productsPage.isTitleDisplayed());
        assertEquals(productsPage.checkTitleName(), "Products");

        for (String s : goodsList) {
            productsPage.addGoodsToCart(s);
        }

        productsPage.addGoodsToCart(2);

        assertEquals(productsPage.checkCounterValue(), "4");
        assertEquals(productsPage.checkCounterColor(), "rgba(226, 35, 26, 1)");
    }
}
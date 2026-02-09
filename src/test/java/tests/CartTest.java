package tests;

import org.testng.annotations.Test;

import static enums.TitleNaming.CART;
import static enums.TitleNaming.PRODUCTS;
import static org.testng.Assert.*;
import static user.UserFactory.withAdminPermission;

public class CartTest extends BaseTest {
    final String goodsName = "Sauce Labs Onesie";

    @Test
    public void checkGoodsAdded() {
        loginPage.open();
        loginPage.login(withAdminPermission());
        assertEquals(productsPage.checkTitleName(), PRODUCTS.getDisplayName());

        productsPage.addGoodsToCart(goodsName);
        productsPage.switchToCart();

        assertEquals(cartPage.checkTitleName(), CART.getDisplayName());

        assertEquals(cartPage.getProductsNames().size(), 1);
        assertFalse(cartPage.getProductsNames().isEmpty());
        assertTrue(cartPage.getProductsNames().contains(goodsName));
    }
}

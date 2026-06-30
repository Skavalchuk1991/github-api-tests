package com.solvd.githubapi.web.test;

import com.solvd.githubapi.web.component.ProductCard;
import com.solvd.githubapi.web.page.InventoryPage;
import com.solvd.githubapi.web.page.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class SauceDemoTest extends AbstractWebTest {

    private static final String VALID_USER = "standard_user";
    private static final String LOCKED_USER = "locked_out_user";
    private static final String PASSWORD = "secret_sauce";

    @Test
    public void testSuccessfulLoginShowsInventory() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();

        InventoryPage inventoryPage = loginPage.login(VALID_USER, PASSWORD);

        Assert.assertTrue(inventoryPage.isInventoryListDisplayed(),
                "Inventory list should be displayed after successful login");
    }

    @Test
    public void testLockedOutUserShowsError() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();

        loginPage.login(LOCKED_USER, PASSWORD);

        Assert.assertTrue(loginPage.isErrorDisplayed(),
                "Error message should be displayed for locked out user");
        Assert.assertTrue(loginPage.getErrorText().contains("locked out"),
                "Error message should mention user is locked out");
    }

    @Test
    public void testAddSingleProductToCart() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();
        InventoryPage inventoryPage = loginPage.login(VALID_USER, PASSWORD);

        List<ProductCard> products = inventoryPage.getProductCards();
        Assert.assertFalse(products.isEmpty(), "There should be products on the inventory page");

        ProductCard firstProduct = products.get(0);
        firstProduct.clickAddToCart();

        Assert.assertTrue(inventoryPage.isCartBadgeDisplayed(),
                "Cart badge should appear after adding a product");
        Assert.assertEquals(inventoryPage.getCartBadgeCount(), "1",
                "Cart badge should show 1 item");
    }

    @Test
    public void testAddMultipleProductsToCart() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();
        InventoryPage inventoryPage = loginPage.login(VALID_USER, PASSWORD);

        List<ProductCard> products = inventoryPage.getProductCards();
        Assert.assertTrue(products.size() >= 3, "There should be at least 3 products");

        products.get(0).clickAddToCart();
        products.get(1).clickAddToCart();
        products.get(2).clickAddToCart();

        Assert.assertEquals(inventoryPage.getCartBadgeCount(), "3",
                "Cart badge should show 3 items after adding 3 products");
    }

    @Test
    public void testSortProductsByPriceLowToHigh() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();
        InventoryPage inventoryPage = loginPage.login(VALID_USER, PASSWORD);

        inventoryPage.sortBy("Price (low to high)");

        List<ProductCard> products = inventoryPage.getProductCards();
        double firstPrice = parsePrice(products.get(0).getProductPrice());
        double secondPrice = parsePrice(products.get(1).getProductPrice());

        Assert.assertTrue(firstPrice <= secondPrice,
                "Products should be sorted by price ascending");
    }

    @Test
    public void testLogoutReturnsToLoginPage() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();
        InventoryPage inventoryPage = loginPage.login(VALID_USER, PASSWORD);

        Assert.assertTrue(inventoryPage.isInventoryListDisplayed(),
                "Should be on inventory page before logout");

        inventoryPage.openMenu();
        inventoryPage.logout();

        Assert.assertTrue(loginPage.isPageOpened(),
                "Should return to login page after logout");
    }

    private double parsePrice(String priceText) {
        return Double.parseDouble(priceText.replace("$", "").trim());
    }
}

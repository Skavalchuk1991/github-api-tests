package com.solvd.githubapi.web.page;

import com.solvd.githubapi.web.component.ProductCard;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class InventoryPage extends AbstractPage {

    @FindBy(css = ".inventory_list")
    private ExtendedWebElement inventoryList;

    @FindBy(css = ".inventory_item")
    private List<ProductCard> productCards;

    @FindBy(css = ".shopping_cart_badge")
    private ExtendedWebElement cartBadge;

    @FindBy(css = ".shopping_cart_link")
    private ExtendedWebElement cartIcon;

    @FindBy(css = "select.product_sort_container")
    private ExtendedWebElement sortDropdown;

    @FindBy(id = "react-burger-menu-btn")
    private ExtendedWebElement menuButton;

    @FindBy(id = "logout_sidebar_link")
    private ExtendedWebElement logoutLink;

    public InventoryPage(WebDriver driver) {
        super(driver);
        setPageAbsoluteURL("https://www.saucedemo.com/inventory.html");
    }

    public List<ProductCard> getProductCards() {
        return productCards;
    }

    public boolean isCartBadgeDisplayed() {
        return cartBadge.isElementPresent(3);
    }

    public String getCartBadgeCount() {
        return cartBadge.getText();
    }

    public void sortBy(String value) {
        sortDropdown.select(value);
    }

    public void openMenu() {
        menuButton.click();
    }

    public void logout() {
        logoutLink.click();
    }

    public boolean isInventoryListDisplayed() {
        return inventoryList.isElementPresent(5);
    }
}

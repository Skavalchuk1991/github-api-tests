package com.solvd.githubapi.web.component;

import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.By;

public class ProductCard extends AbstractUIObject {

    @FindBy(css = ".inventory_item_name")
    private ExtendedWebElement productName;

    @FindBy(css = ".inventory_item_price")
    private ExtendedWebElement productPrice;

    @FindBy(css = "button.btn_inventory")
    private ExtendedWebElement addToCartButton;

    public ProductCard(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public ProductCard(WebDriver driver, By by) {
        super(driver);
        setRootBy(by);
    }

    public String getProductName() {
        return productName.getText();
    }

    public String getProductPrice() {
        return productPrice.getText();
    }

    public void clickAddToCart() {
        addToCartButton.click();
    }

    public String getButtonText() {
        return addToCartButton.getText();
    }
}

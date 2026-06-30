package com.solvd.githubapi.web.page;

import com.zebrunner.carina.webdriver.gui.AbstractPage;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebDriver;

public class LoginPage extends AbstractPage {

    @FindBy(id = "user-name")
    private ExtendedWebElement usernameInput;

    @FindBy(id = "password")
    private ExtendedWebElement passwordInput;

    @FindBy(id = "login-button")
    private ExtendedWebElement loginButton;

    @FindBy(css = "[data-test='error']")
    private ExtendedWebElement errorMessage;

    public LoginPage(WebDriver driver) {
        super(driver);
        setPageAbsoluteURL("https://www.saucedemo.com/");
    }

    public InventoryPage login(String username, String password) {
        usernameInput.type(username);
        passwordInput.type(password);
        loginButton.click();
        return new InventoryPage(getDriver());
    }

    public boolean isErrorDisplayed() {
        return errorMessage.isElementPresent(3);
    }

    public String getErrorText() {
        return errorMessage.getText();
    }
}

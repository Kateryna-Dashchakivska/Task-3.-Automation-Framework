package com.kateProjects.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage extends AbstractPage {

    WebElement cart_element;
    private static final By SEARCH_INPUT_LOCATOR = By.id("search_query_top");
    private static final By GO_BUTTON_LOCATOR = By.xpath("//button[@name='submit_search']");
    private static final By CART_BLOCK_LOCATOR = By.className("shopping_cart");
    private static final By CART_BLOCK_REMOVE_ITEMS_LOCATOR = By.className("ajax_cart_block_remove_link");
    private static final By SIGN_IN_LOCATOR = By.className("login");
    private static final String URL_ = "http://automationpractice.com/index.php";

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(URL_);
    }

    public void fillSearchInput(String query) {
        List<WebElement> elements = driver.findElements(SEARCH_INPUT_LOCATOR);
        WebElement element = elements.get(0);
        element.sendKeys(query);
    }

    public SearchResultsPage pressGo() {
        List<WebElement> elements = driver.findElements(GO_BUTTON_LOCATOR);
        WebElement element = elements.get(0);
        element.click();
        return new SearchResultsPage(driver);
    }

    public AuthenticationPage pressSignInLink() {
        List<WebElement> elements = driver.findElements(SIGN_IN_LOCATOR);
        WebElement element = elements.get(0);
        element.click();
        return new AuthenticationPage(driver);
    }
}
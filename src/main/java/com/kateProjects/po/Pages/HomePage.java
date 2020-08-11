package com.kateProjects.po.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class HomePage extends AbstractPage {

    WebElement cart_element;
    private static final By SEARCH_INPUT_LOCATOR = By.id("search_query_top");
    private static final By GO_BUTTON_LOCATOR = By.xpath("//button[@name='submit_search']");
    private static final By CART_BLOCK_LOCATOR = By.className("shopping_cart");
    private static final By CART_BLOCK_REMOVE_ITEMS_LOCATOR = By.className("ajax_cart_block_remove_link");
    private static final By SIGN_IN_LOCATOR = By.className("login");
    private static final By WOMAN_TAB_LOCATOR = By.className("sf-with-ul");
    private static final By WOMAN_SUB_MENU_LOCATOR = By.xpath("//ul[@class='submenu-container clearfix first-in-line-xs']//ul//li//a[contains(text(),'Evening Dresses')]");
    private static final String URL_ = "http://automationpractice.com/index.php";

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(URL_);
    }

    enum SubMenuItem {
        CASUAL_DRESSES,
        EVENING_DRESSES,
        SUMMER_DRESSES;
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

    public SearchResultsPage openWomenSubMenu(String subMenuName) {  // TODO: 8/5/2020 how to hover and click on sub element?
        Actions actions = new Actions(driver);
        List<WebElement> elements = driver.findElements(WOMAN_TAB_LOCATOR);
        WebElement womanTab = elements.get(0);
        actions.moveToElement(womanTab);
        List<WebElement> elements2 = driver.findElements(WOMAN_SUB_MENU_LOCATOR);
        WebElement womanSubItem = elements2.get(0);
        actions.moveToElement(womanSubItem);
        actions.click().build().perform();

        return new SearchResultsPage(driver);
    }

}
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
    //private static final By WOMAN_SUB_MENU_LOCATOR = By.xpath("//ul[contains(@class, 'submenu-container')]/li/a");
    private static final By WOMAN_SUB_MENU_LOCATOR = By.xpath("//ul[contains(@class, 'submenu-container')]/li/ul/li/a");
    //private static final By WOMAN_SUB_MENU_LOCATOR = By.xpath("//ul[@class='submenu-container clearfix first-in-line-xs']//ul//li//a[contains(text(),'Evening Dresses')]");
    private static final String URL_ = "http://automationpractice.com/index.php";

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(URL_);
    }

    public enum WomenSubMenu{
        T_SHIRTS (0),
        BLOUSES (1),
        CASUAL_DRESSES (2),
        EVENING_DRESSES(3),
        SUMMER_DRESSES(4);

        private final int subMenuValue;

        WomenSubMenu(int subMenuValue) {
            this.subMenuValue = subMenuValue;
        }

        public int getWomenSubMenuItem() {
            return this.subMenuValue;
        }

    }

    public SearchResultsPage openWomenTab() {
        Actions actions = new Actions(driver);
        List<WebElement> elements = driver.findElements(WOMAN_TAB_LOCATOR);
        WebElement womanTab = elements.get(0);
        actions.moveToElement(womanTab).click();
        return new SearchResultsPage(driver);
    }

    public SearchResultsPage openWomenSubMenu(WomenSubMenu womenSubMenu) {
        Actions actions = new Actions(driver);
        List<WebElement> elements = driver.findElements(WOMAN_TAB_LOCATOR);
        WebElement womanTab = elements.get(0);
        actions.moveToElement(womanTab);
        List<WebElement> elements2 = driver.findElements(WOMAN_SUB_MENU_LOCATOR);

        WebElement womanElementToClick = elements2.get(womenSubMenu.getWomenSubMenuItem());
        actions.moveToElement(womanElementToClick);
        actions.click().build().perform();
        return new SearchResultsPage(driver);
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
        return new SearchResultsPage(driver);   //TODO combine 72-83 fillSearchInput + pressGo
    }

//    public void search(String value) {   // instead of 72-83
//        WebElement search = driver.findElement(HEADER_SEARCH_INPUT_LOCATOR);
//        search.sendKeys(value);
//        search.sendKeys(Keys.ENTER);
//    }

    public AuthenticationPage pressSignInLink() {
        List<WebElement> elements = driver.findElements(SIGN_IN_LOCATOR);
        WebElement element = elements.get(0);
        element.click();
        return new AuthenticationPage(driver);
    }

}
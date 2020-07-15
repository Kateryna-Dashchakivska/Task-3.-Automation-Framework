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

    /*public void clearCartPopup() {
        Actions actions = new Actions(driver);
        List<WebElement> cart_blocks = driver.findElements(CART_BLOCK_LOCATOR);
        if (cart_blocks.isEmpty()){
            System.out.println("No Cart element is found!");
        } else {
            actions.moveToElement(cart_blocks.get(0)).moveToElement(driver.findElement(By.xpath("//a[@class='ajax_cart_block_remove_link']"))).click().build().perform();
            WebElement remove_icon = driver.findElement(CART_BLOCK_REMOVE_ITEMS_LOCATOR);
            actions.moveToElement(remove_icon);
            actions.click().build().perform();
            Actions actions1 = actions.click();
            Action actions2 = actions1.build();
            actions2.perform();
            WebElement aaa = driver.findElement(By.xpath("//a[@class='ajax_cart_block_remove_link']"));
            WebElement aaa = driver.findElement(By.xpath("//a[@class='ajax_cart_block_remove_link']"));
            WebElement aaa = driver.findElement(By.xpath("//div[@class='cart_block block exclusive']"));
            WebDriverWait wait0 = new WebDriverWait(driver, 15);
            wait0.until(ExpectedConditions.elementToBeClickable(aaa));
            aaa.click();
            actions.moveToElement(aaa);
            WebElement aaa2 = ;
            WebDriverWait wait = new WebDriverWait(driver, 15);
            wait.until(ExpectedConditions.elementToBeClickable(aaa2));
            aaa2.click();
            actions.moveToElement(cart_blocks.get(0)).perform();
            driver.click();
        }
    }*/
}
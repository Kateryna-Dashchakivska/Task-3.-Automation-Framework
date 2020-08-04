package com.kateProjects.po.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ItemPage extends AbstractPage {

    WebElement elementToClick;
    private static final By ITEM_PRICE_LOCATOR = By.cssSelector ("#our_price_display");
    private static final By ADD_TO_CART_BUTTON_LOCATOR = By.xpath("//span[contains(text(),'Add to cart')]");
    private static final By ITEM_DROPDOWN_SIZE_LOCATOR = By.id ("group_1");
    private static final By BLUE_COLOR_LOCATOR = By.id ("color_14");
    private static final By INCREASE_QUANTITY_LOCATOR = By.className("icon-plus");
    private static final By DECREASE_QUANTITY_LOCATOR = By.className("icon-minus");

    public ItemPage(WebDriver driver) {
        super(driver);
    }

     public Double getItemPrice () {
         return Double.parseDouble(driver.findElement(ITEM_PRICE_LOCATOR).getText().substring(1));
     }

    public CartModal addToCartFirstItem (){
        Actions action = new Actions(driver);
        List<WebElement> elements = driver.findElements(ADD_TO_CART_BUTTON_LOCATOR);
        WebElement elementToClick = elements.get(0);
        action.moveToElement(elementToClick).click().build().perform();
        return new CartModal(driver);
    }

    public void selectItemSize (String size){
        List<WebElement> elements = driver.findElements(ITEM_DROPDOWN_SIZE_LOCATOR);
        WebElement element = elements.get(0);
        Select dropdown = new Select(element);
        dropdown.selectByVisibleText(size);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public void selectItemColor (String color){
        Actions action = new Actions(driver);
        List<WebElement> elements = driver.findElements(BLUE_COLOR_LOCATOR);
        WebElement elementToClick = elements.get(0);
        action.moveToElement(elementToClick).click().build().perform();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public void addItemsQuantity (int itemQuantityToIncrease){
        Actions action = new Actions(driver);
        List<WebElement> elements = driver.findElements(INCREASE_QUANTITY_LOCATOR);
        WebElement elementToClick = elements.get(0);
        for (int currentItemQuantity = 0; currentItemQuantity < itemQuantityToIncrease; currentItemQuantity ++){
            action.moveToElement(elementToClick).click().build().perform();
        }
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public void removeItemQuantity( int itemQuantityToDecrease ){
        Actions action = new Actions(driver);
        List<WebElement> elements = driver.findElements(DECREASE_QUANTITY_LOCATOR);
        WebElement elementToClick = elements.get(0);
        for (int currentItemQuantity = 0; currentItemQuantity < itemQuantityToDecrease; currentItemQuantity++){
            action.moveToElement(elementToClick).click().build().perform();
        }
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
}
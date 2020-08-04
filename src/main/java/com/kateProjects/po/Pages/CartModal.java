package com.kateProjects.po.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CartModal extends AbstractPage {
    WebElement element;
    private static final By ITEM_QUANTITY_LOCATOR = By.id("layer_cart_product_quantity");
    private static final By ITEM_COLOR_AND_SIZE_LOCATOR = By.id("layer_cart_product_attributes");

    public CartModal(WebDriver driver) {
        super(driver);
    }

    public Integer getQuantityNumber () {
        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.visibilityOfElementLocated(ITEM_QUANTITY_LOCATOR));
        List<WebElement> elements = driver.findElements(ITEM_QUANTITY_LOCATOR);
        WebElement element = elements.get(0);
        String quantity = element.getText();
        return Integer.parseInt(quantity);
    }

    public String getItemColor () {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(ITEM_COLOR_AND_SIZE_LOCATOR));
        List<WebElement> elements = driver.findElements(ITEM_COLOR_AND_SIZE_LOCATOR);
        WebElement element = elements.get(0);
        String colorAndSize = element.getText();
        int iend = colorAndSize.indexOf(",");
        String color = colorAndSize.substring(0 , iend); //this will give a text before coma
        return color;
    }

    public String getItemSize () {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(ITEM_COLOR_AND_SIZE_LOCATOR));
        List<WebElement> elements = driver.findElements(ITEM_COLOR_AND_SIZE_LOCATOR);
        WebElement element = elements.get(0);
        String colorAndSize = element.getText();
        String size = colorAndSize.substring(colorAndSize.length() - 1);
        return size;
    }
}
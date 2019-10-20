package com.kateProjects.po;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartModal extends AbstractPage{
    WebElement element;
    private static final By ITEM_QUANTITY_LOCATOR = By.id("layer_cart_product_quantity");
    private static final By ITEM_SIZE_LOCATOR = By.id("layer_cart_product_attributes");


    public CartModal(WebDriver driver) {
        super(driver);
    }

    public Integer getQuantityNumber () {
        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.visibilityOfElementLocated(ITEM_QUANTITY_LOCATOR));
        element = driver.findElement(ITEM_QUANTITY_LOCATOR);
        String quantity = element.getText();
        System.out.println(quantity);
        return Integer.parseInt(quantity);
    }

    public String getItemSize () {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(ITEM_SIZE_LOCATOR));
        element = driver.findElement(ITEM_SIZE_LOCATOR);
        String colorAndSize = element.getText();
        String size = colorAndSize.substring(colorAndSize.length() - 1);
        System.out.println(size);
        return size;
    }

}

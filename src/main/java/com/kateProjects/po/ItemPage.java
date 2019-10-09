package com.kateProjects.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ItemPage extends AbstractPage {

    private static final By ITEM_PRICE_LOCATOR = By.cssSelector ("#our_price_display");

    public ItemPage(WebDriver driver) {
        super(driver);
    }

//    public String getItemPrice (){
//       return driver.findElement(ITEM_PRICE_LOCATOR).getText();
//    }

     public Double getItemPrice () {
         return Double.parseDouble(driver.findElement(ITEM_PRICE_LOCATOR).getText().substring(1));
     }

}

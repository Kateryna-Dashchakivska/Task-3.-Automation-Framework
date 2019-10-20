package com.kateProjects.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class SearchResultsPage extends AbstractPage{

    private static final By FIRST_RESULT_LINK_LOCATOR = By.className("product_img_link");

    WebElement elementToHover;
    WebElement elementToClick;

    public SearchResultsPage (WebDriver driver)
    {
        super(driver);
    }

        public ItemPage openFirstItem (){
            Actions action = new Actions(driver);
            elementToHover = driver.findElement(FIRST_RESULT_LINK_LOCATOR);
            action.moveToElement(elementToHover).click().perform();
            return new ItemPage(driver);
        }

}

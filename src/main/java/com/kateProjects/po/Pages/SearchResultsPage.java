package com.kateProjects.po.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class SearchResultsPage extends AbstractPage {

    //private static final By FIRST_RESULT_LINK_LOCATOR = By.className("product_img_link");
    private static final By FIRST_RESULT_LINK_LOCATOR = By.className("left-block");
    private static final By HEADER_NAME_LOCATOR = By.className("cat-name");

    WebElement elementToHover;

    public SearchResultsPage (WebDriver driver)
    {
        super(driver);
    }

        public ItemPage openFirstItem (){
            Actions action = new Actions(driver);
            List<WebElement> elements = driver.findElements(FIRST_RESULT_LINK_LOCATOR);
            WebElement elementToHover = elements.get(0);
           // action.moveToElement(elementToHover);
            elementToHover.click();
            driver.switchTo().frame(0);
            return new ItemPage(driver);
        }

        public String getHeaderName (){
            List<WebElement> elements = driver.findElements(HEADER_NAME_LOCATOR);
            WebElement headerElement = elements.get(0);
            String headerName = headerElement.getText();
            return headerName;
        }
}
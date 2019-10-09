package com.kateProjects.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends AbstractPage {

    private static final By SEARCH_INPUT_LOCATOR = By.id("search_query_top");
    private static final By GO_BUTTON_LOCATOR = By.xpath("//button[@name='submit_search']");
    private static final String URL_ = "http://automationpractice.com/index.php";

    public HomePage (WebDriver driver){
        super(driver);
    }

    public void open(){
        driver.get(URL_);
    }

    public void fillSearchInput (String query){
        WebElement element = driver.findElement(SEARCH_INPUT_LOCATOR);
        element.sendKeys(query);
    }

    public SearchResultsPage pressGo (){
        driver.findElement(GO_BUTTON_LOCATOR).click();
        return new SearchResultsPage(driver);
    }

}

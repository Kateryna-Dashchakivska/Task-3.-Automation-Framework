package com.kateProjects.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoggedInPage extends AbstractPage {

    WebElement element;

    private static final By USER_HEADER_LOCATOR = By.className("header_user_info");
    private static final By SIGN_OUT_LOCATOR = By.className("logout");
    private static final By ERROR_LOCATOR = By.cssSelector("div[class='alert alert-danger'");


    public LoggedInPage(WebDriver driver)
    {
        super(driver);
    }


    public String getUserName () {
        // TODO: change to findElements!!!
        return driver.findElement(USER_HEADER_LOCATOR).getText();
    }

    public String getLoginError() {
        return driver.findElement(ERROR_LOCATOR).getText();
    }

    public LoggedOutPage signOut (){
        driver.findElement(SIGN_OUT_LOCATOR).click();
        return new LoggedOutPage(driver);
    }



}

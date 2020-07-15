package com.kateProjects.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

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
        List<WebElement> elements = driver.findElements(USER_HEADER_LOCATOR);
        WebElement element = elements.get(0);
        return element.getText();
    }

    public String getLoginError() {
        List<WebElement> elements = driver.findElements(ERROR_LOCATOR);
        WebElement element = elements.get(0);
        return element.getText();
    }

    public LoggedOutPage signOut (){

        List<WebElement> elements = driver.findElements(SIGN_OUT_LOCATOR);
        WebElement element = elements.get(0);
        element.click();
        return new LoggedOutPage(driver);
    }
}
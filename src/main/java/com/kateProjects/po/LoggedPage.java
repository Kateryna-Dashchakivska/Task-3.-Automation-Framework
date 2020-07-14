package com.kateProjects.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoggedPage extends AbstractPage {

    WebElement element;

    private static final By USER_HEADER_LOCATOR = By.className("header_user_info");

    public LoggedPage (WebDriver driver)
    {
        super(driver);
    }



    public String getUserName () {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(USER_HEADER_LOCATOR));
        element = driver.findElement(USER_HEADER_LOCATOR);
        String user = element.getText();
        return user;
    }




}

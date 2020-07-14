package com.kateProjects.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoggedOutPage extends AbstractPage {

    WebElement element;

   // private static final By USER_HEADER_LOCATOR = By.className("header_user_info");
    private static final By SIGN_IN_LOCATOR = By.className("login");


    public LoggedOutPage(WebDriver driver)
    {
        super(driver);
    }

    public String getSignInLink () {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(SIGN_IN_LOCATOR));
        element = driver.findElement(SIGN_IN_LOCATOR);
        String signInLink = element.getText();
        return signInLink;
    }
}

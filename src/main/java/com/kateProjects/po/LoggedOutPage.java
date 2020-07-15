package com.kateProjects.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class LoggedOutPage extends AbstractPage {

    WebElement element;

    private static final By SIGN_IN_LOCATOR = By.className("login");

    public LoggedOutPage(WebDriver driver)
    {
        super(driver);
    }

    public String getSignInLink () {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(SIGN_IN_LOCATOR));
        List<WebElement> elements = driver.findElements(SIGN_IN_LOCATOR);
        WebElement element = elements.get(0);
        String signInLink = element.getText();
        return signInLink;
    }
}
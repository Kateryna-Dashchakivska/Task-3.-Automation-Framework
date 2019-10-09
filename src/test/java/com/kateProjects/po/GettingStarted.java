package com.kateProjects.po;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class GettingStarted {
    @Test
    public void testGoogleSearch() throws MalformedURLException, InterruptedException {
        WebDriver driver = new RemoteWebDriver(
                new URL("http://127.0.0.1:9515"),
                new ChromeOptions());

        HomePage homePage = new HomePage(driver);
        homePage.open();

//      Optional. If not specified, WebDriver searches the PATH for chromedriver.
//      System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
//      WebDriver driver = new ChromeDriver();

        driver.get("http://www.google.com/");
        Thread.sleep(5000);  // Let the user actually see something!
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("ChromeDriver");
        searchBox.submit();
        Thread.sleep(5000);  // Let the user actually see something!
        driver.quit();
    }
}
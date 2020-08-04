package com.kateProjects.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverFactory {

    private static WebDriver driver;

    private DriverFactory(){
        //prevent instantiation
    }

    public static WebDriver getChromeDriver() {

        if (driver == null) {
            try {
                driver = new RemoteWebDriver(
                        new URL("http://127.0.0.1:9515"),
                        new ChromeOptions());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return driver;
    }

    public static void browserSetUp() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().fullscreen();
    }

    public static void kill(){
        driver.manage().deleteAllCookies();
        driver.quit();
        driver = null;   //without it > NoSuchSessionException: Session ID is null.
    }
}
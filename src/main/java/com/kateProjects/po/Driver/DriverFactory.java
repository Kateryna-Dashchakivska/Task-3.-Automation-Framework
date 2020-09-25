package com.kateProjects.po.Driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverFactory {

    private static WebDriver driver;

    private DriverFactory(){
        //prevent instantiation
    }

    public static WebDriver getDriver(String browser) {

        if (driver == null) {
            try {
                if (browser.equalsIgnoreCase("chrome")){
                    driver = new RemoteWebDriver(
                            new URL("http://127.0.0.1:9515"),
                            new ChromeOptions());
                }else if (browser.equalsIgnoreCase("firefox")){
                    driver = new RemoteWebDriver(
                            new URL("http://127.0.0.1:4444"),
                            new FirefoxOptions());
                }else {
                    throw new RuntimeException(browser + " is unknown browser!");
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return driver;
    }

    public static void browserSetUp() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    public static void kill(){
        driver.manage().deleteAllCookies();
        //driver.close();
        driver.quit();   //with it onTestFailure unable to make screenshot
        driver = null;   //without it > NoSuchSessionException: Session ID is null.
    }
}
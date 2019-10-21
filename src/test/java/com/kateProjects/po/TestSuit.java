package com.kateProjects.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TestSuit {

    WebDriver driver;

    {
        try {
            driver = new RemoteWebDriver(
                        new URL("http://127.0.0.1:9515"),
                        new ChromeOptions());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @BeforeClass(description = "Start browser")
    private void initBrowser() throws MalformedURLException {
        System.setProperty("webdriver.chrome.driver", "src/main/java/resources/chromedriver.exe");
        driver.manage().timeouts().pageLoadTimeout(25, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test(description = "Price Check Test")
    public void PriceCheckTest() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.fillSearchInput("dress");
        SearchResultsPage searchResultsPage = homePage.pressGo();
        ItemPage itemPage = searchResultsPage.openFirstItem();
        Double itemPrice = itemPage.getItemPrice();
        Assert.assertTrue(itemPrice < 30, "Price is too expensive!");
    }

    @Test(description = "Change Item Size Test")
    public void ChangeItemSizeTest() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.fillSearchInput("dress");
        SearchResultsPage searchResultsPage = homePage.pressGo();
        ItemPage itemPage = searchResultsPage.openFirstItem();
        itemPage.selectItemSize("L");
        CartModal cartModal = itemPage.addToCartFirstItem();
        String size = cartModal.getItemSize();
        Assert.assertTrue(size.equals("L"), "Size is not changed!");

    }

    @Test(description = "Change Item Color Test")
    public void ChangeItemColorTest() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.fillSearchInput("dress");
        SearchResultsPage searchResultsPage = homePage.pressGo();
        ItemPage itemPage = searchResultsPage.openFirstItem();
        itemPage.selectItemColor("Blue");
        CartModal cartModal = itemPage.addToCartFirstItem();
        String color = cartModal.getItemColor();
        Assert.assertTrue(color.equals("Blue"), "Color is not changed!");

    }

    @Test(description = "Adding to Cart Test")
    public void AddingToCartTest() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.fillSearchInput("dress");
        SearchResultsPage searchResultsPage = homePage.pressGo();
        ItemPage itemPage = searchResultsPage.openFirstItem();
        CartModal cartModal = itemPage.addToCartFirstItem();
        Integer itemQuantity = cartModal.getQuantityNumber();
        Assert.assertTrue(itemQuantity == 1, "Added more than one item to the Cart!");
    }

     @AfterClass(description = "Close browser")
            public void kill(){
        driver.quit();
     }

}

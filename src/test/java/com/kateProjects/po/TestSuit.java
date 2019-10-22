package com.kateProjects.po;

import org.junit.After;
import org.junit.Before;
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

    private static WebDriver driver;

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
    private static void initBrowser() throws MalformedURLException {
        System.setProperty("webdriver.chrome.driver", "src/main/java/resources/chromedriver.exe");
        driver.manage().timeouts().pageLoadTimeout(25, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Before // setup()
    public void beforeEachTestMethod() {
        System.out.println("Setting it up!");
    }

    @After // tearDown()
    public void afterEachTestMethod() {
        System.out.println("Running: tearDown");
    }

    @Test(description = "Price Check Test")
    public void PriceCheckTest() throws Exception {
        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.fillSearchInput("dress");
        SearchResultsPage searchResultsPage = homePage.pressGo();
        ItemPage itemPage = searchResultsPage.openFirstItem();
        Double itemPrice = itemPage.getItemPrice();
        Assert.assertTrue(itemPrice < 30, "Price is too expensive!");
    }

    @Test(description = "Change Item Size Test")
    public void ChangeItemSizeTest() throws Exception {
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
    public void ChangeItemColorTest() throws Exception {
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

    @Test(description = "Add Item Quantity Test")
    public void AddItemQuantityTest() throws Exception {
        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.fillSearchInput("dress");
        SearchResultsPage searchResultsPage = homePage.pressGo();
        ItemPage itemPage = searchResultsPage.openFirstItem();
        itemPage.addItemsQuantity(3);
        CartModal cartModal = itemPage.addToCartFirstItem();
        Integer itemQuantity = cartModal.getQuantityNumber();
        Assert.assertTrue(itemQuantity == 4, "Quantity is changed incorrectly! " +
                "Expected: 4 but got: " + itemQuantity);
    }

    @Test(description = "Remove Item Quantity Test")
    public void RemoveItemQuantityTest() throws Exception {
        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.fillSearchInput("dress");
        SearchResultsPage searchResultsPage = homePage.pressGo();
        ItemPage itemPage = searchResultsPage.openFirstItem();
        itemPage.addItemsQuantity(10);
        itemPage.removeItemQuantity(5);
        CartModal cartModal = itemPage.addToCartFirstItem();
        Integer itemQuantity = cartModal.getQuantityNumber();
        Assert.assertTrue(itemQuantity == 7, "Quantity is changed incorrectly! " +
                "Expected: 7 but got: " + itemQuantity);
        }


    @Test(description = "Adding to Cart Test")
    public void AddingToCartTest() throws Exception {
        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.fillSearchInput("dress");
        SearchResultsPage searchResultsPage = homePage.pressGo();
        ItemPage itemPage = searchResultsPage.openFirstItem();
        CartModal cartModal = itemPage.addToCartFirstItem();
        Integer itemQuantity = cartModal.getQuantityNumber();
        Assert.assertTrue(itemQuantity == 1, "Added more than one item to the Cart!" +
                "Expected: 1 but got: " + itemQuantity);
    }

     @AfterClass(description = "Close browser")
            public static void kill(){
        driver.quit();
     }

}

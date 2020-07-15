package com.kateProjects.po;

import org.junit.*;
import org.junit.rules.TestName;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SmokeTestSuit {
    private static WebDriver driver;
    private static HomePage homePage;

    @Rule
    public TestName name = new TestName();

    @Before // setup()
    public void beforeEachTestMethod()throws MalformedURLException {
        try {
            driver = new RemoteWebDriver(
                    new URL("http://127.0.0.1:9515"),
                    new ChromeOptions());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.manage().timeouts().pageLoadTimeout(25, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        homePage = new HomePage(driver);
    }

    @After // tearDown()
    public void afterEachTestMethod() {
//        System.out.println("Running: tearDown");
//        homePage.open();
//        homePage.clearCartPopup();
//        System.out.println("Cart is cleared!");
        driver.close();
    }

    @Test
    public void PriceCheckTest() throws Exception {
        System.out.println("Starting " + name.getMethodName());
        homePage.open();
        homePage.fillSearchInput("dress");
        SearchResultsPage searchResultsPage = homePage.pressGo();
        ItemPage itemPage = searchResultsPage.openFirstItem();
        Double itemPrice = itemPage.getItemPrice();
        org.testng.Assert.assertTrue(itemPrice < 30, "Price is too expensive!");
    }

    @Test
    public void ChangeItemSizeTest() throws Exception {
        System.out.println("Starting " + name.getMethodName());
        homePage.open();
        homePage.fillSearchInput("dress");
        SearchResultsPage searchResultsPage = homePage.pressGo();
        ItemPage itemPage = searchResultsPage.openFirstItem();
        itemPage.selectItemSize("L");
        CartModal cartModal = itemPage.addToCartFirstItem();
        String size = cartModal.getItemSize();
        org.testng.Assert.assertTrue(size.equals("L"), "Size is not changed!");
    }

    @Test
    public void ChangeItemColorTest() throws Exception {
        System.out.println("Starting " + name.getMethodName());
        homePage.open();
        homePage.fillSearchInput("dress");
        SearchResultsPage searchResultsPage = homePage.pressGo();
        ItemPage itemPage = searchResultsPage.openFirstItem();
        itemPage.selectItemColor("Blue");
        CartModal cartModal = itemPage.addToCartFirstItem();
        String color = cartModal.getItemColor();
        org.testng.Assert.assertTrue(color.equals("Blue"), "Color is not changed!");
    }

    @Test
    public void AddItemQuantityTest() throws Exception {
        System.out.println("Starting " + name.getMethodName());
        homePage.open();
        homePage.fillSearchInput("dress");
        SearchResultsPage searchResultsPage = homePage.pressGo();
        ItemPage itemPage = searchResultsPage.openFirstItem();
        itemPage.addItemsQuantity(3);
        CartModal cartModal = itemPage.addToCartFirstItem();
        Integer itemQuantity = cartModal.getQuantityNumber();
        org.testng.Assert.assertTrue(itemQuantity == 4, "Quantity is changed incorrectly! " +
                "Expected: 4 but got: " + itemQuantity);
    }

    @Test
    public void RemoveItemQuantityTest() throws Exception {
        System.out.println("Starting " + name.getMethodName());
        homePage.open();
        homePage.fillSearchInput("dress");
        SearchResultsPage searchResultsPage = homePage.pressGo();
        ItemPage itemPage = searchResultsPage.openFirstItem();
        itemPage.addItemsQuantity(10);
        itemPage.removeItemQuantity(3);
        CartModal cartModal = itemPage.addToCartFirstItem();
        Integer itemQuantity = cartModal.getQuantityNumber();
        org.testng.Assert.assertTrue(itemQuantity == 8, "Quantity is changed incorrectly! " +
                "Expected: 7 but got: " + itemQuantity);
    }

    @Test
    public void AddingToCartTest() throws Exception {
        System.out.println("Starting " + name.getMethodName());
        homePage.open();
        homePage.fillSearchInput("dress");
        SearchResultsPage searchResultsPage = homePage.pressGo();
        ItemPage itemPage = searchResultsPage.openFirstItem();
        CartModal cartModal = itemPage.addToCartFirstItem();
        Integer itemQuantity = cartModal.getQuantityNumber();
        org.testng.Assert.assertTrue(itemQuantity == 1, "Added more than one item to the Cart!" +
                "Expected: 1 but got: " + itemQuantity);
    }

    @AfterClass
    public static void kill(){
        driver.quit();
    }

}
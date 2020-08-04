package com.kateProjects.po;

import com.kateProjects.po.Pages.CartModal;
import com.kateProjects.po.Pages.HomePage;
import com.kateProjects.po.Pages.ItemPage;
import com.kateProjects.po.Pages.SearchResultsPage;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

//@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class SmokeTestSuit {

    private static HomePage homePage;

//    @Rule
//    public TestName name = new TestName();

    @BeforeMethod
    public void setUp(){
        WebDriver driver = DriverFactory.getChromeDriver();
        DriverFactory.browserSetUp();
        homePage = new HomePage(driver);
        homePage.open();
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.kill();
    }

    @Test
    public void PriceCheckTest() throws Exception {
        //System.out.println("Starting " + name.getMethodName());
        homePage.open();
        homePage.fillSearchInput("dress");
        SearchResultsPage searchResultsPage = homePage.pressGo();
        ItemPage itemPage = searchResultsPage.openFirstItem();
        Double itemPrice = itemPage.getItemPrice();
        org.testng.Assert.assertTrue(itemPrice < 30, "Price is too expensive!");
    }

    @Test
    public void ChangeItemSizeTest() throws Exception {
        //System.out.println("Starting " + name.getMethodName());
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
        //System.out.println("Starting " + name.getMethodName());
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
        //System.out.println("Starting " + name.getMethodName());
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
        //System.out.println("Starting " + name.getMethodName());
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
        //System.out.println("Starting " + name.getMethodName());
        homePage.open();
        homePage.fillSearchInput("dress");
        SearchResultsPage searchResultsPage = homePage.pressGo();
        ItemPage itemPage = searchResultsPage.openFirstItem();
        CartModal cartModal = itemPage.addToCartFirstItem();
        Integer itemQuantity = cartModal.getQuantityNumber();
        org.testng.Assert.assertTrue(itemQuantity == 1, "Added more than one item to the Cart!" +
                "Expected: 1 but got: " + itemQuantity);
    }

}
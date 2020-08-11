package com.kateProjects.po;

import com.kateProjects.po.Pages.*;
import com.kateProjects.po.User.UserHelper;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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
        homePage.fillSearchInput("dress");
        SearchResultsPage searchResultsPage = homePage.pressGo();
        ItemPage itemPage = searchResultsPage.openFirstItem();
        Double itemPrice = itemPage.getItemPrice();
        Assert.assertTrue(itemPrice < 30, "Price is too expensive!");
    }

    @Test
    public void ChangeItemSizeTest() throws Exception {
        //System.out.println("Starting " + name.getMethodName());
        homePage.fillSearchInput("dress");
        SearchResultsPage searchResultsPage = homePage.pressGo();
        ItemPage itemPage = searchResultsPage.openFirstItem();
        itemPage.selectItemSize("L");
        CartModal cartModal = itemPage.addToCartFirstItem();
        String size = cartModal.getItemSize();
        Assert.assertTrue(size.equals("L"), "Size is not changed!");
    }

    @Test
    public void ChangeItemColorTest() throws Exception {
        //System.out.println("Starting " + name.getMethodName());
        homePage.fillSearchInput("dress");
        SearchResultsPage searchResultsPage = homePage.pressGo();
        ItemPage itemPage = searchResultsPage.openFirstItem();
        itemPage.selectItemColor("Blue");
        CartModal cartModal = itemPage.addToCartFirstItem();
        String color = cartModal.getItemColor();
        Assert.assertTrue(color.equals("Blue"), "Color is not changed!");
    }

    @Test
    public void AddItemQuantityTest() throws Exception {
        //System.out.println("Starting " + name.getMethodName());
        homePage.fillSearchInput("dress");
        SearchResultsPage searchResultsPage = homePage.pressGo();
        ItemPage itemPage = searchResultsPage.openFirstItem();
        itemPage.addItemsQuantity(3);
        CartModal cartModal = itemPage.addToCartFirstItem();
        Integer itemQuantity = cartModal.getQuantityNumber();
        Assert.assertTrue(itemQuantity == 4, "Quantity is changed incorrectly! " +
                "Expected: 4 but got: " + itemQuantity);
    }

    @Test
    public void RemoveItemQuantityTest() throws Exception {
        //System.out.println("Starting " + name.getMethodName());
        homePage.fillSearchInput("dress");
        SearchResultsPage searchResultsPage = homePage.pressGo();
        ItemPage itemPage = searchResultsPage.openFirstItem();
        itemPage.addItemsQuantity(10);
        itemPage.removeItemQuantity(3);
        CartModal cartModal = itemPage.addToCartFirstItem();
        Integer itemQuantity = cartModal.getQuantityNumber();
        Assert.assertTrue(itemQuantity == 8, "Quantity is changed incorrectly! " +
                "Expected: 7 but got: " + itemQuantity);
    }

    @Test
    public void AddingToCartTest() throws Exception {
        //System.out.println("Starting " + name.getMethodName());
        homePage.fillSearchInput("dress");
        SearchResultsPage searchResultsPage = homePage.pressGo();
        ItemPage itemPage = searchResultsPage.openFirstItem();
        CartModal cartModal = itemPage.addToCartFirstItem();
        Integer itemQuantity = cartModal.getQuantityNumber();
        Assert.assertTrue(itemQuantity == 1, "Added more than one item to the Cart!" +
                "Expected: 1 but got: " + itemQuantity);
    }

    @Test
    public void OpenEveningDressesSubItemTest() throws Exception {
        //System.out.println("Starting " + name.getMethodName());
        SearchResultsPage resultHeader = homePage.openWomenSubMenu("EVENING_DRESSES");
        String headerName = resultHeader.getHeaderName();
        Assert.assertTrue(headerName.equals("EVENING DRESSES "), "Header is not correct or page is not opened!");
    }

    @Test
    public void AddingToWishlistWhileLoggedOutTest() throws Exception {
        //System.out.println("Starting " + name.getMethodName());
        homePage.fillSearchInput("dress");
        SearchResultsPage searchResultsPage = homePage.pressGo();
        ItemPage itemPage = searchResultsPage.openFirstItem();
        itemPage.addToWishList();
        String wishListError = itemPage.getWishlistMessage();
        Assert.assertTrue(wishListError.equals("You must be logged in to manage your wishlist."), "Message is incorrect or you are logged in!");
    }

    @Test
    public void AddingToWishlistWhileLoggedInTest() throws Exception {
        //System.out.println("Starting " + name.getMethodName());

        AuthenticationPage authPage = homePage.pressSignInLink();
        authPage.signInWithUser(UserHelper.getTestUser());

        homePage.fillSearchInput("dress");
        SearchResultsPage searchResultsPage = homePage.pressGo();
        ItemPage itemPage = searchResultsPage.openFirstItem();
        itemPage.addToWishList();
        String wishListMessage = itemPage.getWishlistMessage();
        Assert.assertTrue(wishListMessage.equals("Added to your wishlist."), "Message is incorrect or you are logged in!");
    }

}
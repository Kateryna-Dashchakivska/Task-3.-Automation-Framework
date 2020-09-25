package com.kateProjects.po;

import com.kateProjects.po.BussinessFlows.Flows;
import com.kateProjects.po.Driver.DriverFactory;
import com.kateProjects.po.Pages.FilterSectionPage;
import com.kateProjects.po.Pages.HomePage;
import com.kateProjects.po.Pages.SearchResultsPage;
import com.kateProjects.po.Pages.WomanResultPage;
import com.kateProjects.po.TestUtils.Listener;
import org.testng.Assert;
import org.testng.annotations.*;

@Listeners(Listener.class)
public class FilteringTests {

    //Development of Filtering Tests is on hold since it doesn't work from the UI!

    WomanResultPage womanResultPage = new WomanResultPage(DriverFactory.getDriver("chrome"));
    FilterSectionPage filterSectionPage = new FilterSectionPage(DriverFactory.getDriver("chrome"));
    HomePage homePage;

    @BeforeMethod
    public void setUp() {
        homePage = Flows.createHomePage();
        homePage.open();
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.kill();
    }

    @Test
    public void OpenEveningDressesSubItemTest() throws Exception {
        //System.out.println("Starting " + name.getMethodName());
        SearchResultsPage resultHeader = Flows.createHomePage().openWomenSubMenu(HomePage.WomenSubMenu.EVENING_DRESSES);
        String headerName = resultHeader.getHeaderName();
        Assert.assertTrue(headerName.equals("EVENING DRESSES "), "Header is not correct or page is not opened!");
    }

    @Test
    public void SingleFilterTest() throws Exception {
        homePage.openWomenTab();
        filterSectionPage.setFilters(FilterSectionPage.FilterCheckBoxes.AVAILABILITY_IN_STOCK);
//        Assert.assertTrue(user.equals("Kateryna Test"), "User Name is incorrect or you are not logged in! "  +
//                "Expected: 'Kateryna Test' but got: " + user);
    }

    @Test
    public void MultipleFilterTest() throws Exception {
        homePage.openWomenTab();
        filterSectionPage.setFilters(FilterSectionPage.FilterCheckBoxes.AVAILABILITY_IN_STOCK);
        filterSectionPage.setFilters(FilterSectionPage.FilterCheckBoxes.COMPOSITIONS_POLYESTER);
        filterSectionPage.setFilters(FilterSectionPage.FilterCheckBoxes.PROPERTIES_MAXI_DRESS);
        filterSectionPage.setFilters(FilterSectionPage.FilterCheckBoxes.STYLES_CASUAL);

//        Assert.assertTrue(user.equals("Kateryna Test"), "User Name is incorrect or you are not logged in! "  +
//                "Expected: 'Kateryna Test' but got: " + user);
    }

    @Test
    public void SortTest() throws Exception {
        homePage.openWomenTab();

        Assert.assertTrue(womanResultPage.sortResults(WomanResultPage.SortBy.PRICE_ASC), "Failed to sort!");


//        Assert.assertTrue(user.equals("Kateryna Test"), "User Name is incorrect or you are not logged in! "  +
//                "Expected: 'Kateryna Test' but got: " + user);
    }

}

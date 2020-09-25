package com.kateProjects.po.BussinessFlows;

import com.kateProjects.po.Driver.DriverFactory;
import com.kateProjects.po.Pages.HomePage;
import com.kateProjects.po.User.User;
import com.kateProjects.po.User.UserHelper;
import org.openqa.selenium.WebDriver;

public class Flows {

    static WebDriver driver;

    public static HomePage createHomePage() {
        driver = DriverFactory.getDriver("firefox");
        DriverFactory.browserSetUp();
        return new HomePage(driver);
    }

    public static void  start() {
        createHomePage().open();
    }

    public static HomePage signInAndSearch(String searchString) { //TODO: return
        HomePage homePage = createHomePage();
        homePage.open();

        signInWithUser(UserHelper.getTestUser(), homePage);
        homePage.fillSearchInput(searchString);
        return homePage;
    }

    private static void signInWithUser(User user, HomePage homePage) { //TODO: return bool
        homePage.pressSignInLink().signInWithUser(UserHelper.getTestUser());
    }

}

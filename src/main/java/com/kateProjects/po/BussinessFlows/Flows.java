package com.kateProjects.po.BussinessFlows;

import com.kateProjects.po.Driver.DriverFactory;
import com.kateProjects.po.Pages.AuthenticationPage;
import com.kateProjects.po.Pages.HomePage;
import com.kateProjects.po.User.UserHelper;
import org.openqa.selenium.WebDriver;

public class Flows {

    private static HomePage homePage;

    public static HomePage getHomePage() {
        return homePage;
    }


    public static void start (){
        WebDriver driver = DriverFactory.getChromeDriver();
        DriverFactory.browserSetUp();
        homePage = new HomePage(driver);
        homePage.open();
    }

    public static void searchByLoggedUser(String searchString){
        AuthenticationPage authPage = homePage.pressSignInLink();
        authPage.signInWithUser(UserHelper.getTestUser());
        homePage.fillSearchInput(searchString);
    }
}

package com.kateProjects.po;

import org.junit.*;
import org.junit.rules.TestName;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoginTestSuit {

    private static WebDriver driver;
    private static HomePage homePage;

    @Rule public TestName name = new TestName();

    @Before // setup()
    public void beforeEachTestMethod()throws MalformedURLException{
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
    public void SignInTest() throws Exception {
        System.out.println("Starting " + name.getMethodName());
        homePage.open();
        AuthenticationPage authPage = homePage.pressSignInLink();
        authPage.enterEmail("kate_d_test16@mail.com");
        authPage.enterPassword("123456");
        LoggedInPage loggedInPage = authPage.pressSignInButton();
        String user = loggedInPage.getUserName();
        Assert.assertTrue(user.equals("Kateryna Test"), "User Name is incorrect or you are not logged in! "  +
                "Expected: Kateryna Test but got: " + user);
    }

    @Test
    public void SignOutTest() throws Exception {
        System.out.println("Starting " + name.getMethodName());
        homePage.open();
        AuthenticationPage authPage = homePage.pressSignInLink();
        authPage.enterEmail("kate_d_test16@mail.com");
        authPage.enterPassword("123456");
        LoggedInPage loggedInPage = authPage.pressSignInButton();
        String user = loggedInPage.getUserName();
        Assert.assertTrue(user.equals("Kateryna Test"), "User Name is incorrect or you are not logged in! "  +
                "Expected: 'Kateryna Test' but got: " + user);

        LoggedOutPage loggedOutPage = loggedInPage.signOut();
        String signInLink = loggedOutPage.getSignInLink();
        Assert.assertTrue(signInLink.equals("Sign in"), "'Sign in' label is incorrect or you are not logged out! "  +
                "Expected: 'Sign in' but got: " + signInLink);
    }

    @Test
    public void EmptyCredentialTest() throws Exception {
        System.out.println("Starting " + name.getMethodName());
        homePage.open();
        AuthenticationPage authPage = homePage.pressSignInLink();

        //Verify when both email and password are empty
        LoggedInPage loggedInPage = authPage.pressSignInButton();
        String loginError = loggedInPage.getLoginError();
        Assert.assertTrue(loginError.equals("There is 1 error\nAn email address required."),
                "There are no Errors or Error is not correct!" +
                "Expected: 'There is 1 error\nAn email address required.' but received: " + loginError);

        //Verify when only password is empty
        authPage.enterEmail("kate_d_test16@mail.com");
        loggedInPage = authPage.pressSignInButton();
        loginError = loggedInPage.getLoginError();
        // TODO: change Strings!!!
        Assert.assertTrue(loginError.equals("There is 1 error\nAn email address required."),
                "There are no Errors or Error is not correct!" +
                        "Expected: 'There is 1 error\nAn email address required.' but received: " + loginError);

        authPage.clearCredentials();

        //Verify when only email is empty
        authPage.enterPassword("123456");
        loggedInPage = authPage.pressSignInButton();
        loginError = loggedInPage.getLoginError();
        // TODO: change Strings!!!
        Assert.assertTrue(loginError.equals("There is 1 error\nAn email address required."),
                "There are no Errors or Error is not correct!" +
                        "Expected: 'There is 1 error\nAn email address required.' but received: " + loginError);

    }

    @Test
    public void InvalidEmailsTest() throws Exception {
        System.out.println("Starting " + name.getMethodName());
        homePage.open();
        AuthenticationPage authPage = homePage.pressSignInLink();

        List<String> invalidEmails = Arrays.asList("kate_d_test16@mail.", "kate_d_test16@mail", "kate_d_test16mail.com",
                "@mail.com", "kate_d_test16@mail,com", "kate_d_test16@@mail.com");
        LoggedInPage loginPage;
        String loginError;
        for (String email: invalidEmails) {
            authPage.enterEmail(email);
            authPage.enterPassword("123456");

            loginPage = authPage.pressSignInButton();
            loginError = loginPage.getLoginError();

            Assert.assertTrue(loginError.equals("There is 1 error\nInvalid email address."),
                    "There are no Errors or Error is not correct!" +
                    "Expected: 'There is 1 error\nInvalid email address.' but received: " + loginError);
            //reset fields to avoid concatenation:
            authPage.clearCredentials();
        }
    }

     @AfterClass
     public static void kill(){
        driver.quit();
     }

}

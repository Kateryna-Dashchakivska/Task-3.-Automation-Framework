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
        driver.close();
    }

    @Test
    public void SignInTest() throws Exception {
        System.out.println("Starting " + name.getMethodName());
        homePage.open();
        AuthenticationPage authPage = homePage.pressSignInLink();
        LoggedInPage loggedInPage = authPage.signInWithUser(UserHelper.getTestUser());
        String user = loggedInPage.getUserName();
        Assert.assertTrue(user.equals("Kateryna Test"), "User Name is incorrect or you are not logged in! "  +
                "Expected: 'Kateryna Test' but got: " + user);
    }

    @Test
    public void SignOutTest() throws Exception {
        StringBuilder result = new StringBuilder();
        System.out.println("Starting " + name.getMethodName());
        homePage.open();
        AuthenticationPage authPage = homePage.pressSignInLink();

        LoggedInPage loggedInPage = authPage.signInWithUser(UserHelper.getTestUser());
        String user = loggedInPage.getUserName();
        SoftAssert.assertTrueAppendResult(user.equals("Kateryna Test"),
                "User Name is incorrect or you are not logged in! " +
                        "Expected: 'Kateryna Test' but got: " + user, result);

        LoggedOutPage loggedOutPage = loggedInPage.signOut();
        String signInLink = loggedOutPage.getSignInLink();
        SoftAssert.assertTrueAppendResult(signInLink.equals("Sign in"),
                "'Sign in' label is incorrect or you are not logged out! " +
                        "Expected: 'Sign in' but got: " + signInLink, result);

        //Final system assert:
        Assert.assertTrue(result.length() == 0, "The test(s) failed with result: " + result);
    }


    @Test
    public void EmptyCredentialTest() throws Exception {
        StringBuilder result = new StringBuilder();
        System.out.println("Starting " + name.getMethodName());
        homePage.open();
        AuthenticationPage authPage = homePage.pressSignInLink();

        //Verify when both email and password are empty
        LoggedInPage loggedInPage = authPage.signInWithUser(UserHelper.getEmptyCredentialsUser());
        String loginError = loggedInPage.getLoginError();
        SoftAssert.assertTrueAppendResult(loginError.equals("There is 1 error\nAn email address required."),
                "Test when both email and password are empty failed. There are no Errors or Error is not correct! " +
                "Expected: 'There is 1 error\nAn email address required.' but received: " + loginError, result);

        //Verify when only password is empty
        User user = UserHelper.getEmptyCredentialsUser();
        user.setEmail(CONSTANT.LOGIN_EMAIL);
        loggedInPage = authPage.signInWithUser(user);
        loginError = loggedInPage.getLoginError();
        SoftAssert.assertTrueAppendResult(loginError.equals("There is 1 error\nPassword is required."),
                "Test when only password is empty failed. There are no Errors or Error is not correct! " +
                "Expected: 'There is 1 error\nPassword is required.' but received: " + loginError, result);

        authPage.clearCredentials();

        //Verify when only email is empty
        user.setEmail("");
        user.setPassword(CONSTANT.VALID_ENCRYPTED_PASSWORD);
        loggedInPage = authPage.signInWithUser(user);
        loginError = loggedInPage.getLoginError();
        SoftAssert.assertTrueAppendResult(loginError.equals("There is 1 error\nAn email address required."),
                "Test when only email is empty failed. There are no Errors or Error is not correct! " +
                "Expected: 'There is 1 error\nAn email address required.' but received: " + loginError, result);

        //Final system assert:
        Assert.assertTrue(result.length() == 0, "The test(s) failed with result: " + result);
    }

    @Test
    public void InvalidEmailsTest() throws Exception {
        System.out.println("Starting " + name.getMethodName());

        homePage.open();
        AuthenticationPage authPage = homePage.pressSignInLink();

        /*List<String> invalidEmails = Arrays.asList("kate_d_test16@mail.", "kate_d_test16@mail", "kate_d_test16mail.com",
                "@mail.com", "kate_d_test16@mail,com", "kate_d_test16@@mail.com");*/
        LoggedInPage loginPage;
        String loginError;


        for (User user: UserHelper.createUsersWithInvalidEmail()) {
            loginPage = authPage.signInWithUser(user);
            loginError = loginPage.getLoginError();

            Assert.assertTrue(loginError.equals("There is 1 error\nInvalid email address."),
                    "There are no Errors or Error is not correct! " +
                    "Expected: 'There is 1 error\nInvalid email address.' but received: " + loginError);
            //reset fields to avoid concatenation:
            authPage.clearCredentials();
        }
    }

    @Test
    public void UnregisteredEmailTest() throws Exception {
        System.out.println("Starting " + name.getMethodName());
        homePage.open();
        AuthenticationPage authPage = homePage.pressSignInLink();
        User user = UserHelper.getEmptyCredentialsUser();
        user.setEmail(CONSTANT.LOGIN_UNREGISTERED_EMAIL);
        user.setPassword(CONSTANT.VALID_ENCRYPTED_PASSWORD);
        LoggedInPage loggedInPage = authPage.signInWithUser(user);
        String loginError = loggedInPage.getLoginError();
        Assert.assertTrue(loginError.equals("There is 1 error\nAuthentication failed."),
                "There are no Errors or Error is not correct! " +
                        "Expected: 'There is 1 error\nAuthentication failed.' but received: " + loginError);
    }

    @Test
    public void InvalidPasswordTest() throws Exception {
        System.out.println("Starting " + name.getMethodName());
        homePage.open();
        AuthenticationPage authPage = homePage.pressSignInLink();
        User user = UserHelper.getEmptyCredentialsUser();
        user.setEmail(CONSTANT.LOGIN_EMAIL);
        user.setPassword(CONSTANT.INVALID_ENCRYPTED_PASSWORD);
        LoggedInPage loggedInPage = authPage.signInWithUser(user);
        String loginError = loggedInPage.getLoginError();
        Assert.assertTrue(loginError.equals("There is 1 error\nAuthentication failed."),
                "There are no Errors or Error is not correct! " +
                        "Expected: 'There is 1 error\nAuthentication failed.' but received: " + loginError);
    }

    //test to encrypt password (needs to be removed)
    @Test
    public void EncryptionTest (){
        String originalString = "111111";

        String encryptedString = AES256.encrypt(originalString) ;
        String decryptedString = AES256.decrypt(encryptedString) ;

        System.out.println(originalString);
        System.out.println(encryptedString);
        System.out.println(decryptedString);
    }

     @AfterClass
     public static void kill(){
        driver.quit();
     }
}
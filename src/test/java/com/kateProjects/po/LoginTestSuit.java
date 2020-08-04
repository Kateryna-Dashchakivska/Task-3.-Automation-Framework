package com.kateProjects.po;

import com.kateProjects.po.Assert.SoftAssert;
import com.kateProjects.po.Hashing.AES256;
import com.kateProjects.po.Pages.AuthenticationPage;
import com.kateProjects.po.Pages.HomePage;
import com.kateProjects.po.Pages.LoggedInPage;
import com.kateProjects.po.Pages.LoggedOutPage;
import com.kateProjects.po.Strings.CONSTANT;
import com.kateProjects.po.User.User;
import com.kateProjects.po.User.UserHelper;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.Assert;

//@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class LoginTestSuit {

    HomePage homePage;

    @DataProvider (name = "data-provider")
    public Object[][] dpMethod(){
        return CONSTANT.LOGIN_INVALID_EMAIL;
    }

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

    @Test(description = "To test login")
    public void SignInTest() throws Exception {
        //System.out.println("Starting " + name.getMethodName());
        AuthenticationPage authPage = homePage.pressSignInLink();
        LoggedInPage loggedInPage = authPage.signInWithUser(UserHelper.getTestUser());
        String user = loggedInPage.getUserName();
        Assert.assertTrue(user.equals("Kateryna Test"), "User Name is incorrect or you are not logged in! "  +
                "Expected: 'Kateryna Test' but got: " + user);
    }

    @Test
    public void SignOutTest() throws Exception {
        StringBuilder result = new StringBuilder();
        //System.out.println("Starting " + name.getMethodName());
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
        //System.out.println("Starting " + name.getMethodName());
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

    @Test (dataProvider = "data-provider")
    public void InvalidEmailsTest(String email) throws Exception { // TODO: 7/31/2020 TEST NG Data provider
        //System.out.println("Starting " + name.getMethodName());

        AuthenticationPage authPage = homePage.pressSignInLink();

        User user = new User();
        user.setEmail(email);
        user.setPassword(CONSTANT.VALID_ENCRYPTED_PASSWORD);
        LoggedInPage loginPage = authPage.signInWithUser(user);
        String loginError = loginPage.getLoginError();

        Assert.assertTrue(loginError.equals("There is 1 error\nInvalid email address."),
                    "There are no Errors or Error is not correct! " +
                    "Expected: 'There is 1 error\nInvalid email address.' but received: " + loginError);
            //reset fields to avoid concatenation:
            authPage.clearCredentials();

    }

    @Test
    public void UnregisteredEmailTest() throws Exception {
        //System.out.println("Starting " + name.getMethodName());
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
        //System.out.println("Starting " + name.getMethodName());
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
        //System.out.println("Starting " + name.getMethodName());
        String originalString = "111111";

        String encryptedString = AES256.encrypt(originalString) ;
        String decryptedString = AES256.decrypt(encryptedString) ;

        System.out.println(originalString);
        System.out.println(encryptedString);
        System.out.println(decryptedString);
    }
}
package com.kateProjects.po;

import com.kateProjects.po.Assert.SoftAssert;
import com.kateProjects.po.BussinessFlows.Flows;
import com.kateProjects.po.DataProvider.DataProviderSource;
import com.kateProjects.po.Driver.DriverFactory;
import com.kateProjects.po.Hashing.AES256;
import com.kateProjects.po.Pages.AuthenticationPage;
import com.kateProjects.po.Pages.LoggedInPage;
import com.kateProjects.po.Pages.LoggedOutPage;
import com.kateProjects.po.Strings.Constant;
import com.kateProjects.po.TestUtils.Listener;
import com.kateProjects.po.User.User;
import com.kateProjects.po.User.UserHelper;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

//@FixMethodOrder(MethodSorters.NAME_ASCENDING)

@Listeners(Listener.class)
public class LoginTestSuit {

    @BeforeMethod
    public void setUp(){
        Flows.start();
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.kill();
    }

    @Test(description = "To test login")
    public void SignInTest() throws Exception {
        //System.out.println("Starting " + name.getMethodName());
        AuthenticationPage authPage = Flows.getHomePage().pressSignInLink();
        LoggedInPage loggedInPage = authPage.signInWithUser(UserHelper.getTestUser());
        String user = loggedInPage.getUserName();
        Assert.assertTrue(user.equals("Kateryna Test"), "User Name is incorrect or you are not logged in! "  +
                "Expected: 'Kateryna Test' but got: " + user);
    }

    @Test
    public void SignOutTest() throws Exception {
        StringBuilder result = new StringBuilder();
        //System.out.println("Starting " + name.getMethodName());
        AuthenticationPage authPage = Flows.getHomePage().pressSignInLink();

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
        AuthenticationPage authPage = Flows.getHomePage().pressSignInLink();

        //Verify when both email and password are empty
        LoggedInPage loggedInPage = authPage.signInWithUser(UserHelper.getEmptyCredentialsUser());
        String loginError = loggedInPage.getLoginError();
        SoftAssert.assertTrueAppendResult(loginError.equals("There is 1 error\nAn email address required."),
                "Test when both email and password are empty failed. There are no Errors or Error is not correct! " +
                "Expected: 'There is 1 error\nAn email address required.' but received: " + loginError, result);

        //Verify when only password is empty
        User user = UserHelper.getEmptyCredentialsUser();
        user.setEmail(Constant.LOGIN_EMAIL);
        loggedInPage = authPage.signInWithUser(user);
        loginError = loggedInPage.getLoginError();
        SoftAssert.assertTrueAppendResult(loginError.equals("There is 1 error\nPassword is required."),
                "Test when only password is empty failed. There are no Errors or Error is not correct! " +
                "Expected: 'There is 1 error\nPassword is required.' but received: " + loginError, result);

        authPage.clearCredentials();

        //Verify when only email is empty
        user.setEmail("");
        user.setPassword(Constant.VALID_ENCRYPTED_PASSWORD);
        loggedInPage = authPage.signInWithUser(user);
        loginError = loggedInPage.getLoginError();
        SoftAssert.assertTrueAppendResult(loginError.equals("There is 1 error\nAn email address required."),
                "Test when only email is empty failed. There are no Errors or Error is not correct! " +
                "Expected: 'There is 1 error\nAn email address required.' but received: " + loginError, result);

        //Final system assert:
        Assert.assertTrue(result.length() == 0, "The test(s) failed with result: " + result);
    }

    @Test (dataProvider = "dp-invalid-emails", dataProviderClass = DataProviderSource.class)
    public void InvalidEmailsTest(String email) throws Exception {
        //System.out.println("Starting " + name.getMethodName());

        AuthenticationPage authPage = Flows.getHomePage().pressSignInLink();

        User user = new User();
        user.setEmail(email);
        user.setPassword(Constant.VALID_ENCRYPTED_PASSWORD);
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
        AuthenticationPage authPage = Flows.getHomePage().pressSignInLink();
        User user = UserHelper.getEmptyCredentialsUser();
        user.setEmail(Constant.LOGIN_UNREGISTERED_EMAIL);
        user.setPassword(Constant.VALID_ENCRYPTED_PASSWORD);
        LoggedInPage loggedInPage = authPage.signInWithUser(user);
        String loginError = loggedInPage.getLoginError();
        Assert.assertTrue(loginError.equals("There is 1 error\nAuthentication failed."),
                "There are no Errors or Error is not correct! " +
                        "Expected: 'There is 1 error\nAuthentication failed.' but received: " + loginError);
    }

    @Test
    public void InvalidPasswordTest() throws Exception {
        //System.out.println("Starting " + name.getMethodName());
        AuthenticationPage authPage = Flows.getHomePage().pressSignInLink();
        User user = UserHelper.getEmptyCredentialsUser();
        user.setEmail(Constant.LOGIN_EMAIL);
        user.setPassword(Constant.INVALID_ENCRYPTED_PASSWORD);
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
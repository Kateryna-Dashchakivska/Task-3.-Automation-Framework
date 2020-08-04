    package com.kateProjects.po.Pages;

    import com.kateProjects.po.Hashing.AES256;
    import com.kateProjects.po.User.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

    public class AuthenticationPage extends AbstractPage {

        public AuthenticationPage (WebDriver driver)
        {
            super(driver);
        }

        private User user;

        public User getUser(){
            return this.user;
        }

        public void setUser(User user){
            this.user = user;
        }

        private static final By EMAIL_LOCATOR = By.id("email");
        private static final By PASSWORD_LOCATOR = By.id("passwd");
        private static final By SUBMIT_LOGIN_LOCATOR = By.id("SubmitLogin");

        private void enterEmail(String query) {
            List<WebElement> elements = driver.findElements(EMAIL_LOCATOR);
            WebElement element = elements.get(0);
            element.sendKeys(query);
        }

        private void enterPassword(String query) {    // 123456
            List<WebElement> elements = driver.findElements(PASSWORD_LOCATOR);
            WebElement element = elements.get(0);
            element.sendKeys(query);
        }

        public LoggedInPage signInWithUser(User user) {
            enterEmail(user.getEmail());
            if (user.getPassword().equals("")){
                enterPassword("");
            }    else {
                enterPassword(AES256.decrypt(user.getPassword()));
            }
            List<WebElement> elements = driver.findElements(SUBMIT_LOGIN_LOCATOR);
            WebElement element = elements.get(0);
            element.click();
            return new LoggedInPage(driver);
        }

        public void clearCredentials() {
            List<WebElement> elements = driver.findElements(EMAIL_LOCATOR);
            WebElement emailElement = elements.get(0);
            emailElement.clear();

            List<WebElement> elements2 = driver.findElements(PASSWORD_LOCATOR);
            WebElement passwordElement = elements2.get(0);
            passwordElement.clear();
        }
    }
    package com.kateProjects.po;

    import org.openqa.selenium.By;
    import org.openqa.selenium.WebDriver;
    import org.openqa.selenium.WebElement;

    public class AuthenticationPage extends AbstractPage {

        public AuthenticationPage (WebDriver driver)
        {
            super(driver);
        }

        private static final By EMAIL_LOCATOR = By.id("email");
        private static final By PASSWORD_LOCATOR = By.id("passwd");
        private static final By SUBMIT_LOGIN_LOCATOR = By.id("SubmitLogin");

        public void enterEmail(String query) {
            WebElement element = driver.findElement(EMAIL_LOCATOR);
            element.sendKeys(query);
        }
        public void enterPassword(String query) {    // 123456
            WebElement element = driver.findElement(PASSWORD_LOCATOR);
            element.sendKeys(query);
        }

        public LoggedPage pressSignIn() {
            driver.findElement(SUBMIT_LOGIN_LOCATOR).click();
            return new LoggedPage(driver);
        }




    }





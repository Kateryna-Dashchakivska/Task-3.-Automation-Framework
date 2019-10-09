package com.kateProjects.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class SearchResultsPage extends AbstractPage{

    private static final By FIRST_RESULT_LINK_LOCATOR = By.className("product_img_link");
    private static final By MORE_BUTTON_LOCATOR = By.xpath("//li[@class='ajax_block_product col-xs-12 col-sm-6 col-md-4 first-in-line first-item-of-tablet-line first-item-of-mobile-line hovered']//img[@class='replace-2x img-responsive']");
    WebElement res1;
    public SearchResultsPage (WebDriver driver)
    {
        super(driver);
    }

        public ItemPage openFirstItem (){
            Actions action = new Actions(driver);

            res1 = driver.findElement(FIRST_RESULT_LINK_LOCATOR);
            action.moveToElement(res1).click().perform();
            return new ItemPage(driver);
}

}

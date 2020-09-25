package com.kateProjects.po.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class WomanResultPage extends AbstractPage {

    private static final By SORT_BY_LOCATOR = By.id("selectProductSort");

    public WomanResultPage(WebDriver driver) {
        super(driver);
    }

    public enum SortBy {
        POSITION_ASC,
        PRICE_ASC,
        PRICE_DESC,
        NAME_ASC,
        NAME_DESC,
        QUANTITY_DESC,
        REFERENCE_ASC,
        REFERENCE_DESC
    }


    public boolean sortResults(SortBy typeToSort) {
        List<WebElement> selectElement = driver.findElements(SORT_BY_LOCATOR);
        if (selectElement.isEmpty()) {
            return false;
        }

        String sortString;
        switch (typeToSort) {
            case POSITION_ASC:
                sortString = "position:asc";
                break;

            case PRICE_ASC:
                sortString = "price:asc";
                break;

            case PRICE_DESC:
                sortString = "price:desc";
                break;

            case NAME_ASC:
                sortString = "name:asc";
                break;

            case NAME_DESC:
                sortString = "name:desc";
                break;

            case QUANTITY_DESC:
                sortString = "quantity:desc";
                break;

            case REFERENCE_ASC:
                sortString = "reference:asc";
                break;

            case REFERENCE_DESC:
                sortString = "reference:desc";
                break;

            default:
                return false;

        }
        Select filterDropdown = new Select(selectElement.get(0));
        filterDropdown.selectByValue(sortString);
        return true;
    }

}
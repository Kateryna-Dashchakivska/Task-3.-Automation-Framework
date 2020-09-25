package com.kateProjects.po.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class FilterSectionPage extends AbstractPage {

    private static final By FILTER_CHECKBOXES_LOCATOR = By.xpath("//div[contains(@class, 'layered_filter')]/ul/li/div");

    public FilterSectionPage(WebDriver driver) {
        super(driver);
    }

    public enum FilterCheckBoxes {
        CATEGORIES_TOPS(0),
        CATEGORIES_DRESSES(1),
        SIZE_S(2),
        SIZE_M(3),
        SIZE_L(4),
        COMPOSITIONS_COTTON(5),
        COMPOSITIONS_POLYESTER(6),
        COMPOSITIONS_VISCOSE(7),
        STYLES_CASUAL(8),
        STYLES_DRESSY(9),
        STYLES_GIRLY(10),
        PROPERTIES_COLORFUL_DRESS(11),
        PROPERTIES_MAXI_DRESS(12),
        PROPERTIES_MIDI_DRESS(13),
        PROPERTIES_SHORT_DRESS(14),
        PROPERTIES_SHORT_SLEEVE(15),
        AVAILABILITY_IN_STOCK(16),
        MANUFACTURER_FASHION(17),
        CONDITION_NEW(18);

        private final int checkbox;

        FilterCheckBoxes(int checkbox) {
            this.checkbox = checkbox;
        }

        public int getFilterCheckBoxes() {
            return this.checkbox;
        }

    }

    public FilteredRusultsPage setFilters(FilterCheckBoxes checkBox) {
        Actions actions = new Actions(driver);
        List<WebElement> elements = driver.findElements(FILTER_CHECKBOXES_LOCATOR);
        WebElement checkboxToClick = elements.get(checkBox.getFilterCheckBoxes());
        actions.moveToElement(checkboxToClick);
        actions.click().build().perform();
        return new FilteredRusultsPage(driver);
    }

}

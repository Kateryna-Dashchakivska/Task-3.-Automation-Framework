package com.kateProjects.pajeObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Search
{
    private static WebElement element1 = null;

    public static WebElement searchFiled( WebDriver driver )
    {
        element1 = driver.findElement(By.name("search_query"));
        return element1;
    }

    public static WebElement searchIcon( WebDriver driver )
    {
        element1 = driver.findElement(By.name("submit_search"));
        return element1;
    }






}

package com.kateProjects.pajeObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SearchTest
{
    static WebDriver driver = null;

    public static void main ( String [] args){
        searchItem();
    }

    public static void searchItem()
    {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Kateryna_Dashchakivs\\IdeaProjects\\Task-3.-Automation-Framework\\chromedriver.exe");
        driver = new ChromeDriver();

        driver.get("http://automationpractice.com/index.php");

        driver.findElement(By.name("search_query")).sendKeys("printed dress");

        driver.findElement(By.name("submit_search")).click();

        //driver.close();
    }
}

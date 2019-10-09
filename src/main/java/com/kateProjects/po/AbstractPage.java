package com.kateProjects.po;

import org.openqa.selenium.WebDriver;

public class AbstractPage{

    protected WebDriver driver;
    private static final int WAIT_FOR_ELEMENT_SECONDS = 10;

    public AbstractPage (WebDriver driver){
        this.driver = driver;
    }
}

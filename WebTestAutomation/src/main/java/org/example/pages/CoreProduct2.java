package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CoreProduct2 {

    private WebDriver driver;
    private By menuLocator = By.xpath("//ul[@class='style__menu_3EZdd']/li/a/span[text()='...']");
    private By newsLocator = By.xpath("//*[@id='nba-nav']/div[2]/nav/div/nav[2]/ul/li/nav/ul/li[2]/a");
    private By videoLocator = By.xpath("//time[@aria-label]");

    public CoreProduct2(WebDriver driver) {
        this.driver = driver;
    }
    public By setMenuLocator() throws InterruptedException {
        return menuLocator;
    }

    public By setNewsLocator() throws InterruptedException {
        return newsLocator;
    }



    public By getVideoLocator(){
        return videoLocator;
    }
}

package org.example.pages;

import org.openqa.selenium.*;
import java.util.List;

public class CoreProduct1 {

        private WebDriver driver;
        private By shopLocator = By.xpath("//a[@rel='noreferrer']/span[text()='Shop']");
        private By mensLocator = By.xpath("//*[@id='nba-nav']/div[2]/nav/div/nav[1]/ul/li[4]/nav/ul/li[2]/a");
        private By popUpLocator = By.xpath("/html/body/div[4]/div[1]/div");
        private By cookiesLocator = By.xpath("//*[@id='onetrust-accept-btn-handler']");
        private By jacketLocator = By.xpath("//body/div[2]/div[1]/div[6]/div[1]/div[1]/nav[1]/div[2]/div[2]/div[6]/div[2]/ul[1]/li[8]/a[1]");
        private By priceLocator = By.xpath("//span[@class='money-value']/span[@class='sr-only']");
        private By titleLocator = By.xpath("//div[@class='product-card-title']/a");
        private By nextPageLocator = By.xpath("//li[@class='next-page']/a/i");




        public CoreProduct1(WebDriver driver) {
            this.driver = driver;
        }

        public void setPopUpLocator() throws InterruptedException {
            driver.findElement(popUpLocator).click();
        }

        public void checkCookiesLocator() throws InterruptedException {

           List <WebElement> ele = driver.findElements(cookiesLocator);
            boolean isPresent = ele.isEmpty();

            // If the element is found, click on it
            if (! isPresent) {
                driver.findElement(cookiesLocator).click();
                System.out.println("Element clicked successfully.");
                // Perform further actions after clicking
            }
        }

        public By setShopLocator() throws InterruptedException {
            return shopLocator;
        }

        public void mens_dropdown_option() {
            driver.findElement(mensLocator).click();
        }

        public void setJacketLocator() {
            driver.findElement(jacketLocator).click();
        }

        public List<WebElement> setPriceLocator() {
            List<WebElement> elements = driver.findElements(priceLocator);
            return elements;
        }

        public List<WebElement> setTitleLocator() {
            List<WebElement> elements = driver.findElements(titleLocator);
            return elements;
        }

        public By setNextPageLocator()  {
            return nextPageLocator;
        }





}

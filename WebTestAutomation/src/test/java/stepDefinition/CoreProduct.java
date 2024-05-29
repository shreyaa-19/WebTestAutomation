package stepDefinition;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.example.pages.CoreProduct1;
import org.example.pages.CoreProduct2;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CoreProduct {

    private WebDriver driver;
    private CoreProduct1 coreProduct1 = null;
    private CoreProduct2 coreProduct2 = null;
    public ChromeOptions options = new ChromeOptions();

     @Before
     public void setUp()
     {
         options.addArguments("--remote-allow-origins=*");
         System.setProperty("webdriver.chrome.driver","C:\\Users\\shrey\\IdeaProjects\\WebTestAutomation\\src\\test\\resources\\drivers\\chromedriver.exe");
         //    WebDriverManager.chromedriver().setup();
         driver = new ChromeDriver(options);
         driver.manage().window().maximize();
         driver.get("https://www.nba.com/warriors");
         coreProduct1 = new CoreProduct1(driver);
         coreProduct2 = new CoreProduct2(driver);

     }

    @After
    public void tearDown(){
        if(driver!=null)
            driver.quit();
    }

    @Given("User navigates to home page")
    public void nba_home_page() throws InterruptedException {

        coreProduct1.setPopUpLocator();
        coreProduct1.checkCookiesLocator();

        Duration timeout = Duration.ofSeconds(10);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        WebElement element = null;
        try {
            element = wait.until(ExpectedConditions.visibilityOfElementLocated(coreProduct1.setShopLocator()));
        } catch (NoSuchElementException e) {
            // Handle the case when the element is not found
            System.out.println("Element not found, continuing with other actions...");
            // Perform other actions here if needed
        }

        if(element != null)
        {
            Actions action = new Actions(driver);
            action.moveToElement(element).perform();
        }
    }

    @When("User navigates to Men's section")
    public void mens_section_page() {
         coreProduct1.mens_dropdown_option();
    }

    @Then("User should be able to see Jackets for men")
    public void jacket_section() throws InterruptedException {
       // coreProduct1.mens_jacket_section();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        int k=3;
        // Get the list of all open tabs
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());

        // Switch to the newly opened tab
        driver.switchTo().window(tabs.get(1));
        coreProduct1.setJacketLocator();

        String fileName = "output.txt";
        List<List<String>> data = new ArrayList<>();

        while(k>0) {
            List<WebElement> elements = coreProduct1.setPriceLocator();
            List<WebElement> titleEle = coreProduct1.setTitleLocator();
            int count = elements.size();
            System.out.println("Number of elements: " + count);
            // Iterate over the elements and fetch their text
            for (int i = 0; i < count-1; i++) {
                WebElement element = elements.get(i);
                WebElement element1 = titleEle.get(i);
                String text = element.getText();
                String title = element1.getText();
                data.add(Arrays.asList(text,title));

                //   System.out.println("Text of element " + (i + 1) + ": " + data);
            }
            // boolean isPresent = driver.findElements(cookiesLocator).isEmpty();
            if(k>1)
                driver.findElement(coreProduct1.setNextPageLocator()).click();
            k--;
        }
        writeToFile(fileName, data);

    }

    public void writeToFile(String fileName,List<List<String>> data)
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            // Iterate over the list of lists
            for (List<String> lineData : data) {
                // Join the elements of the list with a comma
                String line = String.join(", ", lineData);
                // Write the line to the file
                writer.write(line);
                // Add a newline character after each line
                writer.newLine();
            }
            // Optional: print a message to indicate success
            System.out.println("File written successfully: " + fileName);
        } catch (IOException e) {
            // Handle any IOException that might occur
            e.printStackTrace();
        }
    }

    @Given("User navigates to News & Features")
    public void navigateNewsAndFeature() throws InterruptedException {

        coreProduct1.setPopUpLocator();
        coreProduct1.checkCookiesLocator();

        Duration timeout = Duration.ofSeconds(10);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        WebElement element = null;
        try {
            element = wait.until(ExpectedConditions.visibilityOfElementLocated(coreProduct2.setMenuLocator()));
        } catch (NoSuchElementException e) {
            // Handle the case when the element is not found
            System.out.println("Element not found, continuing with other actions...");
            // Perform other actions here if needed
        }

        if(element != null)
        {
            Actions action = new Actions(driver);
            action.moveToElement(element).perform();
        }

        WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(coreProduct2.setNewsLocator()));
        element1.click();
    }

    @When("User navigates to Videos section")
    public void navigateToVideos() throws InterruptedException {
        List<WebElement> elements = driver.findElements(coreProduct2.getVideoLocator());
        System.out.println("ytu Videos = " + elements);
        System.out.println("Total Videos = " + elements.size());
    }

    @Then("User needs to fetch video count")
    public void countVideos3D() {
        List<WebElement> elements = driver.findElements(coreProduct2.getVideoLocator());
        int c = 0;
        LocalDate currentDate = LocalDate.now();
        LocalDate threeDaysAgo = currentDate.minusDays(3);

        // Define a date formatter to parse the date from the timestamp
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd yyyy");

        // Iterate through the elements and compare the timestamp with 3 days ago
        for (WebElement element : elements) {
            String timestampText = element.getAttribute("datetime");
            LocalDate timestampDate = LocalDate.parse(timestampText, formatter);

            // Check if the timestamp is greater than or equal to 3 days ago
            if (timestampDate.isEqual(threeDaysAgo) || timestampDate.isBefore(threeDaysAgo)) {
                // Do something with the element
                System.out.println("Element with timestamp greater than or equal to 3 days ago: " + element.getText());
                c++;
            }
        }
        System.out.println("Videos >= 3d : "+c);

    }
}

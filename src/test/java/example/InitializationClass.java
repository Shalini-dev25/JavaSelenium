package example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class InitializationClass {

    protected WebDriver driver;

    @BeforeClass
    public void initChrome() throws InterruptedException {
         driver = new ChromeDriver();
    }

    @AfterClass
    public void tearDown() throws InterruptedException {
        driver.quit();
    }

}

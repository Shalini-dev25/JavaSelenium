package example;

import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
//import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;
import java.util.List;

public class TC002 extends InitializationClass{

    @Test(priority = 1)
    public void sortDropdown() throws InterruptedException {

        driver.get("https://www.wikipedia.org");
        driver.manage().window().maximize();

        /* Select the preferred language link and print the title of the wiki page */
        driver.findElement(By.xpath("//button[@id='js-lang-list-button']")).click();
        Thread.sleep(2000);
        driver.findElement(By.linkText("தமிழ்")).click();
        System.out.println("Navigated to Language Wiki Page : " + driver.getTitle()+ "\r\n");
        Thread.sleep(2000);

        driver.navigate().back();
        Thread.sleep(2000);

        WebElement s1 = driver.findElement(By.xpath("//select[@id='searchLanguage']"));
        Select langElem = new Select(s1);
        System.out.println("Default Option value : " + langElem.getFirstSelectedOption().getText() + "\r\n");
        langElem.selectByValue("ta");
        System.out.println("Modified Option value : " + langElem.getFirstSelectedOption().getText() + "\r\n");

        /* Option Value Comparison using SoftAssert/Assert */
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(langElem.getFirstSelectedOption().getText(), "English");

        /* Sort & Print the options in the Dropdown box */
        List<WebElement> items = langElem.getOptions();
        System.out.println("Languages are " + items.size() + " in total" + "\r\n");
        String[] sortedLang = new String[items.size()];
        int i = 0 ;
        for(WebElement item: items) {
                /* Feeds Array with Option Value */  //sortedLang[i] = item.getDomAttribute("value");
                /* Feeds Array with Option Text */  sortedLang[i] = item.getText();
                i++;
            }
        System.out.println("Language list before Sorting." + "\r\n");
        for(String lang : sortedLang) {
            System.out.println(lang);
        }
        Arrays.sort(sortedLang);
        System.out.println("Language list after Sorting." + "\r\n");
        for(String lang : sortedLang){
            System.out.println(lang);
        }

        //JavascriptExecutor jse = (JavascriptExecutor)driver;
        //jse.executeScript("window.alert('Tamil is selected')");

        softAssert.assertAll();

     //   Thread.sleep(5000);

    }

}

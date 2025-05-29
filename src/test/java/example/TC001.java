package example;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class TC001 extends InitializationClass {

    @Test
    public void priceCompare() throws InterruptedException {

        WebDriver.Options manage = driver.manage();
        manage.window().maximize();
        Thread.sleep(2000);
        manage.deleteAllCookies();

        int amazonPrice = getAmazonPrice();
        int flipKartPrice = getFlipKartPrice();

        System.out.println("Lowest Price of the product 'iPhone 16 Pro Max 256 GB' in Amazon is " + amazonPrice);
        System.out.println("Lowest Price of the product 'iPhone 16 Pro Max 256 GB' in Flipkart is " + flipKartPrice);

        if(amazonPrice < flipKartPrice){
            System.out.println("Product price is lesser in Amazon");
        }
        else if(amazonPrice > flipKartPrice){
            System.out.println("Product price is lesser in FlipKart");
        }
        else if(amazonPrice == flipKartPrice){
            System.out.println("Product price is equal in FlipKart and Amazon");
        }

    }

    private  int getAmazonPrice() throws InterruptedException {

        driver.get("https://www.amazon.in");

        WebElement searchBox = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
        searchBox.sendKeys("iPhone 16 Pro Max 256 GB mobile phones", Keys.ENTER);
        Thread.sleep(5000);

        Select sortType =  new Select(driver.findElement(By.id("s-result-sort-select")));
        sortType.selectByVisibleText("Featured");
        Thread.sleep(5000);


        ArrayList productPrice = (ArrayList) driver.findElements(By.xpath("//div[@data-cy='price-recipe']/div/div/a[starts-with(@href,'/iPhone-16-Pro-Max-256')][1]/span/span/span[@class='a-price-whole']"));

       // System.out.println("Number of products: " + productPrice.size());
        WebElement listedElement = (WebElement) productPrice.get(0);

        String[] price = listedElement.getText().split(",");
        StringBuilder sbprice = new StringBuilder();
        for (String str : price) {
            if(str.contains("₹"))
                str = str.replace('₹',' ');
            sbprice.append(str.trim());
        }

        return Integer.parseInt(sbprice.toString());
    }

    private  int getFlipKartPrice() throws InterruptedException {

        driver.get("https://www.flipkart.com");
     
        WebElement searchBox = driver.findElement(By.xpath("//input[@name='q']"));
        searchBox.sendKeys("iPhone 16 Pro Max 256 GB phone", Keys.ENTER);
       // driver.findElement(By.xpath("//button[@class='_2iLD__']")).click();
        Thread.sleep(3000);

        driver.findElement(By.xpath("//div[@class='zg-M3Z'][2]")).click();
        System.out.println("Results are sorted ascendingly");
        Thread.sleep(2000);

        ArrayList productPrice = (ArrayList) driver.findElements(By.xpath("//div[@class='Nx9bqj _4b5DiR']"));
        System.out.println("Total Number of products: " + productPrice.size());
        WebElement listedElement = (WebElement) productPrice.get(0);
        String[] price = listedElement.getText().split(",");
        StringBuilder sbprice = new StringBuilder();
        for (String str : price) {
            //System.out.println("Str is " + str);
            if(str.contains("₹"))
                str = str.replace('₹',' ');
            sbprice.append(str.trim());
        }

        return Integer.parseInt(sbprice.toString());
    }
}


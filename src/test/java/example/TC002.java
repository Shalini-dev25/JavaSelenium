package example;

import org.testng.annotations.Test;

public class TC002 extends InitializationClass{

    @Test(priority = 1)
    public void printAutoFeed(){
        driver.navigate().to("https://www.amazon.in");
        System.out.println(driver.getTitle());

    }

}

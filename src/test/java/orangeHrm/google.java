package orangeHrm;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class google {
	static String val="Orange HRM demo";    
	static String val2="Google";
	
   public static void getGoogleNavigationMethods(WebDriver driver,String webName,String link) {
	   // Launch the browser and open Google
	   driver.get(link);
       driver.manage().window().maximize();
       driver.manage().deleteAllCookies();
       // Enter search term and submit
       WebElement input = driver.findElement(By.id("APjFqb"));
       input.sendKeys(webName);
       input.sendKeys(Keys.ENTER);

       // Verify search results page
       System.out.println(driver.getTitle());
       if (driver.getTitle().contains(val)) {
           System.out.println("Search results for 'Orange HRM demo' displayed");
       } else {
           System.out.println("Unexpected page title: " + driver.getTitle());
       }

       // Navigate back and verify Google page
       driver.navigate().back();
       if (driver.getTitle().contains(val2)) {
           System.out.println("Back to Google page successful");
       } else {
           System.out.println("Unexpected URL after back: " + driver.getCurrentUrl());
       }

       // Navigate forward and verify search results page
       driver.navigate().forward();
       if (driver.getTitle().contains(val)) {
           System.out.println("Forward to search results successful");
       } else {
           System.out.println("Unexpected page title after forward: " + driver.getTitle());
       }
   }
   
   public static void getGooglenav(WebDriver driver,String initialUrlAfterHrm) {
	   if (driver.getCurrentUrl().equals(initialUrlAfterHrm)) {
           System.out.println("Unexpected URL after closing HRM tab: " + driver.getCurrentUrl());
       } else {
           System.out.println("Closed OrangeHRM tab and navigated back to search results");
       }
	    // Close the browser
       driver.quit();
       System.out.println("Test Completed");
   }
}


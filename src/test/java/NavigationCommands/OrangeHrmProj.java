package NavigationCommands;

import java.io.File;

import java.io.IOException;
import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class OrangeHrmProj {

	WebDriver driver;
        WebDriverWait wait;
        @Parameters({"browser"})
        @BeforeTest
        public void createDriver(String br) {
        	switch(br) {
        	case"chrome":
        		driver = new ChromeDriver();
        		break;
        	case "edge":
        		driver = new EdgeDriver();
        		break;
        	}
        	//open google
            driver.get("https://google.com");
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
            // Enter search term and submit
            WebElement input = driver.findElement(By.xpath("//textarea[@class='gLFyf']"));
            input.sendKeys("Orange HRM demo");
            input.sendKeys(Keys.ENTER);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        }
        @Test()
        public void validation() throws InterruptedException, IOException, NoSuchElementException {
            // Verify search results page
            System.out.println(driver.getTitle());
            if (driver.getTitle().contains("Orange HRM demo")) {
                System.out.println("Search results for 'Orange HRM demo' displayed");
            } else {
                System.out.println("Unexpected page title: " + driver.getTitle());
            }
 
            // Navigate back and verify Google page
            driver.navigate().back();
            if (driver.getTitle().contains("Google")) {
                System.out.println("Back to Google page successful");
            } else {
                System.out.println("Unexpected URL after back: " + driver.getCurrentUrl());
            }
 
            // Navigate forward and verify search results page
            driver.navigate().forward();
            if (driver.getTitle().contains("Orange HRM demo")) {
                System.out.println("Forward to search results successful");
            } else {
                System.out.println("Unexpected page title after forward: " + driver.getTitle());
            }
 
            // Open OrangeHRM in a new tab
            driver.switchTo().newWindow(WindowType.TAB);
            driver.navigate().to("https://www.orangehrm.com/");
 
            // Capture the URL of the OrangeHRM page before closing
            String initialUrlAfterHrm = driver.getCurrentUrl();
 
            // Fill out and submit contact form
            driver.findElement(By.linkText("Contact Sales")).click();
            
            driver.findElement(By.id("Form_getForm_FullName")).sendKeys(ExcelUtils.getExcelValue(1, 0));
            driver.findElement(By.id("Form_getForm_Contact")).sendKeys(ExcelUtils.getExcelValue(1, 1));
            driver.findElement(By.id("Form_getForm_Email")).sendKeys(ExcelUtils.getExcelValue(1, 2));
            driver.findElement(By.id("Form_getForm_JobTitle")).sendKeys(ExcelUtils.getExcelValue(1, 3));

            WebElement countries = driver.findElement(By.id("Form_getForm_Country"));
            Select select = new Select(countries);
            select.selectByValue(ExcelUtils.getExcelValue(1, 4));

            WebElement employees = driver.findElement(By.id("Form_getForm_NoOfEmployees"));
            Select drop = new Select(employees);
            drop.selectByVisibleText(ExcelUtils.getExcelValue(1, 5));
            
            JavascriptExecutor js = (JavascriptExecutor) driver;
            WebElement textarea = driver.findElement(By.id("Form_getForm_Comment"));
            js.executeScript("arguments[0]. scrollIntoView();", textarea);
            Thread.sleep(1000); //for movement of scroller
            //click check box
            driver.findElement(By.id("Nocaptcha-Form_getForm_Captcha")).click();
    		
    		Thread.sleep(60000);
            driver.findElement(By.id("Form_getForm_action_submitForm")).click();
            
           
            //scrolling for better view
            WebElement view=driver.findElement(By.id("Form_getForm_Email"));
            js.executeScript("arguments[0]. scrollIntoView();", view);
            Thread.sleep(1000);  //used for taking screenshot perfect
            
            //To capture the Screenshot
            TakesScreenshot ts=(TakesScreenshot)driver;
            File sourceFile=ts.getScreenshotAs(OutputType.FILE);
            File targetFile=new File(System.getProperty("user.dir")+"\\Screenshot\\Screenshot11.png");
            sourceFile.renameTo(targetFile);
          
            
            driver.findElement(By.id("Form_getForm_Comment")).sendKeys(ExcelUtils.getExcelValue(1, 6));
            
            driver.findElement(By.id("Nocaptcha-Form_getForm_Captcha")).click();
            driver.findElement(By.id("Form_getForm_action_submitForm")).click();
           
            // Close the current tab (OrangeHRM)
            driver.close();
            driver.switchTo().window(driver.getWindowHandles().iterator().next());
            // Verify navigation back to the Google search results tab
            if (driver.getCurrentUrl().equals(initialUrlAfterHrm)) {
                System.out.println("Unexpected URL after closing HRM tab: " + driver.getCurrentUrl());
            } else {
                System.out.println("Closed OrangeHRM tab and navigated back to search results");
            }
 
            // Close the browser
            driver.quit();
            System.out.println("Test Completed");
         } 
        
        public void tearDown() {
        	driver.quit();
        }
}


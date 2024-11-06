package orangeHrm;


import java.awt.AWTException;
import java.time.Duration;


import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

public class OrangeHRMproject {
	
	static String webName="Orange HRM demo";
	static String Link1="https://google.com";
	static String Link2="https://www.orangehrm.com/";
	
	public static void main(String[] args) throws InterruptedException, AWTException {
        WebDriver driver = DriverManager.createDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
       // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
 
        
        try {
            //google navigations
            google.getGoogleNavigationMethods(driver,webName,Link1);

            // Open OrangeHRM in a new tab
            driver.switchTo().newWindow(WindowType.TAB);
            
            driver.navigate().to(Link2);

            // Capture the URL of the OrangeHRM page before closing
            String initialUrlAfterHrm = driver.getCurrentUrl();
           
            
            // Fill out and submit contact form
            orange.getOrangeHRM(driver);
            
            driver.switchTo().window(driver.getWindowHandles().iterator().next());
  
            // Verify navigation back to the Google search results tab
            google.getGooglenav(driver,initialUrlAfterHrm);

            
          } catch (NoSuchElementException e) {
              System.out.println("Element not found: " + e.getMessage());
          } catch (Exception e) {
              System.out.println("An error occurred: " + e.getMessage());
          } finally {
                     if (driver != null) {
                         driver.quit();}
          }
    }
    
   
}


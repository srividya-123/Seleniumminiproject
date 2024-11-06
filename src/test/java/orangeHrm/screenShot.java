package orangeHrm;
import java.io.File;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class screenShot {
	public static void getScreenshot(WebDriver driver,JavascriptExecutor js) throws InterruptedException {
   	 //scrolling for better view
       WebElement view=driver.findElement(By.id("Form_getForm_Email"));
       js.executeScript("arguments[0]. scrollIntoView();", view);        
       Thread.sleep(1000);  //used for taking screenshot perfect
       
       TakesScreenshot ts=(TakesScreenshot)driver;
       File sourceFile=ts.getScreenshotAs(OutputType.FILE);
       File targetFile=new File(System.getProperty("user.dir")+"\\Screenshot\\ss.png");
       sourceFile.renameTo(targetFile);
   }

}

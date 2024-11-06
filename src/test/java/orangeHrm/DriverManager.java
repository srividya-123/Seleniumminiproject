package orangeHrm;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
 
import java.util.Scanner;

public class DriverManager {
	private static final String CHROME_BROWSER = "chrome";
    private static final String EDGE_BROWSER = "edge";
 
    public static WebDriver createDriver() {
        try (Scanner scanner = new Scanner(System.in)) {
			System.out.print("Enter the desired browser (chrome or edge): ");
			String browserName = scanner.nextLine();
 
			switch (browserName.toLowerCase()) {
			    case CHROME_BROWSER:
			        return new ChromeDriver();
			    case EDGE_BROWSER:
			        return new EdgeDriver();
			    default:
			        System.out.println("Unsupported browser: " + browserName);
			        throw new IllegalArgumentException("Unsupported browser: " + browserName);
			}
		}
    }


}

package CATChinaRetail.TestAutomation.Drivers;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverManager extends DriverManager{
	@Override
	public void createWebDriver() {
		 String exePath = "./src/main/resources/chromedriver.exe";
		 System.setProperty("webdriver.chrome.driver", exePath);
		 //WebDriver driver = new ChromeDriver();
		 
		 ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");
			options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
			options.addArguments("--no-sandbox"); // Bypass OS security model

			this.driver = new ChromeDriver(options);
			driver.manage().window().maximize();
		
	}

}

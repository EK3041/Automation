package CATChinaRetail.TestAutomation.Reusables;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import CATChinaRetail.TestAutomation.Core.ApplicationLog;

public class UniversalSearch {
	public static void SearchAndEnter(WebDriver driver,String searchterm) throws IOException {
		
		ApplicationLog.InfoLog("Searching for part : "+searchterm);
		
		driver.findElement(By.id("SimpleSearchForm_SearchTerm")).clear();
		driver.findElement(By.id("SimpleSearchForm_SearchTerm")).sendKeys(searchterm);
		driver.findElement(By.id("SimpleSearchForm_SearchTerm")).sendKeys(Keys.RETURN);// enter

	}

}

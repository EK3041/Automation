package CATChinaRetail.TestAutomation.Reusables;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class LoginPage {
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		driver.manage().timeouts().implicitlyWait(20000, TimeUnit.SECONDS);
	}
	private By countrycodetextbox = By.name("countryCode");
	private By phoneNumberTextBox  = By.id("phonenumber");
	private By passwordtextbox = By.id("password");
	private By loginbutton = By.id("WC_AccountDisplay_links_2");
	
	public void login(String countryCode, String phoneNumber, String password) {
		// this.setcountryCode(countryCode); Since only China +86 is there so no need for this code anymore
		
		this.setphoneNumber(phoneNumber);
		this.setPassword(password);
		this.clickLogin();
	}
	public void setcountryCode(String countryCode) {
		
		Select drpCountryCode = new Select(driver.findElement(countrycodetextbox));
		drpCountryCode.selectByVisibleText(countryCode);
	}
	public void setphoneNumber(String phoneNumber) {
		driver.findElement(phoneNumberTextBox).sendKeys(phoneNumber);
		}
	public void setPassword(String password) {
		driver.findElement(passwordtextbox).sendKeys(password);
	}
	public void clickLogin() {
		driver.findElement(loginbutton).click();
		
	}

}

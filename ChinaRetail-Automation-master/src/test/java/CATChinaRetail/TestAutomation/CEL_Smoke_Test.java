package CATChinaRetail.TestAutomation;

import java.io.IOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import CATChinaRetail.TestAutomation.Core.ApplicationLog;
import CATChinaRetail.TestAutomation.Core.GenerateReport;
import CATChinaRetail.TestAutomation.Core.ReadExcel;
import CATChinaRetail.TestAutomation.Core.UpdateVSTS;
import CATChinaRetail.TestAutomation.Drivers.DriverManager;
import CATChinaRetail.TestAutomation.Drivers.DriverManagerFactory;
import CATChinaRetail.TestAutomation.Drivers.DriverType;
import CATChinaRetail.TestAutomation.Reusables.LoginPage;
import CATChinaRetail.TestAutomation.Reusables.Reusablefunctions;
import CATChinaRetail.TestAutomation.Reusables.UniversalSearch;

public class CEL_Smoke_Test {
	DriverManager driverManager;
	WebDriver driver;
	LoginPage loginPage;
	Reusablefunctions Reusable;
	String[] Dealer_list = { "ECIM", "LSHM", "LSHMNC", "CEL" };

	@Before
	public void setup() throws IOException {
		driverManager = DriverManagerFactory.getDriverManager(DriverType.CHROME);
		driver = driverManager.getWebDriver();
		driver.get("https://wwwqa.catpeijian.cn/en/cat");
		ReadExcel.SheetName("SmokeTest");
	}

	@Test
	public void CELSmokeTest() throws Exception {
		try {
			ReadExcel.TestCaseName("CEL");
			Reusablefunctions.clickOnAccount(driver);
			loginPage = new LoginPage(driver);
			WebDriverWait wait = new WebDriverWait(driver, 40);
			loginPage.login(ReadExcel.ReadColumn("Country"), ReadExcel.ReadColumn("Phone_number"),
					ReadExcel.ReadColumn("Password"));
			// redirect to the desired dealer's URL.
			driver.get(ReadExcel.ReadColumn("DEALER_URL"));
			ApplicationLog.InfoLog("Below is the data of smoke test for " + "CEL" + " Dealer.");
			// select the province
			Reusablefunctions.SelectProvince(wait, driver, ReadExcel.ReadColumn("ProvinceID"));
			Boolean ProvinceBackShadowClosed = wait
					.until(ExpectedConditions.invisibilityOfElementLocated(By.className("province__back--shaddow")));
			// check weather loading animation has closed or not
			// Boolean LoadingOverlayClosed =
			// wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loading_overlay")));
			Boolean LoadingOverlayClosed = Reusablefunctions.LoadingAnimationComplete(wait);
			// search items in universal search bar
			if (LoadingOverlayClosed == true && ProvinceBackShadowClosed == true) {
				UniversalSearch.SearchAndEnter(driver, ReadExcel.ReadColumn("Search_term"));
			}
			LoadingOverlayClosed = Reusablefunctions.LoadingAnimationComplete(wait);
			if (LoadingOverlayClosed == true) {
				// click on BuyNow button
				Boolean ClickBuyNow = Reusablefunctions.ClickBuyNow(wait, driver);
				if (ClickBuyNow == true) {
				} else
					throw null;
			}
			// Select pick up option
			Reusablefunctions.SelectPickUp(wait);
			// Wait for Loading action to complete it will return True if animation has
			// completed
			LoadingOverlayClosed = Reusablefunctions.LoadingAnimationComplete(wait);
			// Select fapiao as No
			if (LoadingOverlayClosed == true) {
				Reusablefunctions.SelectFapiaoNo(wait);
			}
			// accept the term and condition
			Reusablefunctions.AcceptTermAndCond(wait);
			// click on Pay button
			Reusablefunctions.ClickOnPay(wait);
			/*
			 * Since no more actual payment is possible, so removing the code
			 * 
			 * // Select one of the payment option LoadingOverlayClosed =
			 * Reusablefunctions.LoadingAnimationComplete(wait); if(LoadingOverlayClosed ==
			 * true ) { Reusablefunctions.selectPaymentOption(wait); } // click on simulate
			 * Payment Reusablefunctions.clickOnSimulatePayment(driver); //click on Success
			 * payment Alert Box Reusablefunctions.ClickOnSuccessAlert(driver);
			 */
			// Capture the order number after Successful payment option
			LoadingOverlayClosed = Reusablefunctions.LoadingAnimationComplete(wait);
			if (LoadingOverlayClosed == true) {
				String OrderNumber = Reusablefunctions.CaptureOrderNumber(driver, wait);
				ApplicationLog.InfoLog("The pickup order number is " + OrderNumber);
			}
			// from here it is for delivery option
			// enter the search term
			UniversalSearch.SearchAndEnter(driver, ReadExcel.ReadColumn("Search_term"));
			// click on BuyNow button
			LoadingOverlayClosed = Reusablefunctions.LoadingAnimationComplete(wait);
			if (LoadingOverlayClosed == true) {
				// click on BuyNow button
				Boolean ClickBuyNow = Reusablefunctions.ClickBuyNow(wait, driver);
				if (ClickBuyNow == true) {
				} else
					throw null;
			}
			// select delivery option
			LoadingOverlayClosed = Reusablefunctions.LoadingAnimationComplete(wait);
			if (LoadingOverlayClosed == true) {
				Reusablefunctions.SelectDelivery(wait);
			}
			// Wait for Loading action to complete it will return True if animation has
			// completed
			LoadingOverlayClosed = Reusablefunctions.LoadingAnimationComplete(wait);
			// Select fapiao as No
			if (LoadingOverlayClosed == true) {
				Reusablefunctions.SelectFapiaoNo(wait);
			}
			// accept the term and condition
			Reusablefunctions.AcceptTermAndCond(wait);
			// click on Pay button
			Reusablefunctions.ClickOnPay(wait);
			/*
			 * Since no more actual payment is possible, so removing the code
			 *
			 * // Select one of the payment option LoadingOverlayClosed =
			 * Reusablefunctions.LoadingAnimationComplete(wait); if(LoadingOverlayClosed ==
			 * true ) { Reusablefunctions.selectPaymentOption(wait); } // click on simulate
			 * Payment Reusablefunctions.clickOnSimulatePayment(driver); //click on Success
			 * payment Alert Box Reusablefunctions.ClickOnSuccessAlert(driver);
			 */
			// Capture the order number after Successful payment option
			LoadingOverlayClosed = Reusablefunctions.LoadingAnimationComplete(wait);
			if (LoadingOverlayClosed == true) {
				String OrderNumber = Reusablefunctions.CaptureOrderNumber(driver, wait);
				ApplicationLog.InfoLog("The delivery order number is " + OrderNumber);
			}
			Reusablefunctions.clickOnAccount(driver);
			Reusablefunctions.clickOnlogout(wait);
			GenerateReport.Pass();
			UpdateVSTS.PassTestCase("36");
		} catch (Exception e) {
			ApplicationLog.InfoLog("Order could not be placed successfully");
			GenerateReport.Fail(driver);
			UpdateVSTS.FailTestCase("36");
			throw e;
		}
	}

	@After
	public void quit() {
		driverManager.quitWebDriver();
	}
}
package CATChinaRetail.TestAutomation.Reusables;

import CATChinaRetail.TestAutomation.Core.*;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.xml.sax.SAXException;

public class Reusablefunctions {

	public static void clickOnAccount(WebDriver driver)
			throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {

		driver.findElement(By.xpath(ReadXML.NodeName("Account"))).click();
	}

	public static void clickOnlogout(WebDriverWait wait)
			throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
		try {
			wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div[2]/div/div[3]/a")))
					.click();
			// driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div/div[3]/a")).click();
		} catch (Exception e) {
			throw e;
		}
	}

	public static void SelectProvince(WebDriverWait wait, WebDriver driver, String ProvinceID) throws IOException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("provinceName"))).click();

		boolean isPresent = driver.findElements(By.id(ProvinceID)).size() > 0;
		if (isPresent == true) {
			String provinceNameString = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(ProvinceID)))
					.getText();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(ProvinceID))).click();

			ApplicationLog.InfoLog("Selected province is " + provinceNameString);

		}

	}

	public static Boolean ClickBuyNow(WebDriverWait wait, WebDriver driver) throws Exception {
		int xpathCount = driver.findElements(By.id("pdpInventoryStatusQtyChange")).size();
			if (xpathCount > 0) {
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("pdpInventoryStatusQtyChange")));
			}
			if (driver.getPageSource().contains("OUT OF STOCK") || driver.getPageSource().contains("缺货")) {
				ApplicationLog.InfoLog("Item is out of stock");
				return false;
			}
			else {
				String btnColor = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("buyNowBtn")))
						.getCssValue("background-color");
				do {
					Thread.sleep(200);
					btnColor = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("buyNowBtn")))
							.getCssValue("background-color");
				} while (!btnColor.equals("rgba(255, 207, 0, 1)"));
				
				JavascriptExecutor je = (JavascriptExecutor) driver;
				WebElement element = driver.findElement(By.id("buyNowBtn"));
				je.executeScript("arguments[0].scrollIntoView(true);", element);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("buyNowBtn"))).click();
				ApplicationLog.InfoLog("Buy Now button clicked");
				return true;
			}
			
	}

	public static void clickOnSimulatePayment(WebDriver driver) throws IOException, InterruptedException {

		WebElement webElement = driver.findElement(By.id("iframe"));
		Screenshot aliPayScreenshot = new AShot().takeScreenshot(driver, webElement);
		BufferedImage actualImage = aliPayScreenshot.getImage();
		BufferedImage expectedImage = ImageIO.read(new File("./src/main/resources/aliPay.png"));
		ImageDiffer imgDiff = new ImageDiffer();
		ImageDiff diff = imgDiff.makeDiff(expectedImage, actualImage);

		do {
			diff = imgDiff.makeDiff(expectedImage, actualImage);

		} while (diff.hasDiff() == false);

		try {
			driver.findElement(By.id("simulatePayment")).click();
		} catch (Exception e) {
			ApplicationLog.ErrorLog("Unable to click on Simulate Payment button");
		}
	}

	public static String CheckPdPInventory(WebDriverWait wait) {

		String itemstatus = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pdpInventoryStatus")))
				.getText();

		return itemstatus;

	}

	public static void SelectPickUp(WebDriverWait wait) throws Exception {
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[@id=\"deliveryoption\"]/div/div/div/div/div[1]/ul/li[1]/a")))
				.click();
		ApplicationLog.InfoLog("Pickup is Selected");

	}

	public static void SelectDelivery(WebDriverWait wait) throws Exception {
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[@id=\"deliveryoption\"]/div/div/div/div/div[1]/ul/li[2]/a")))
				.click();
		ApplicationLog.InfoLog("Delivery is Selected");

	}

	public static void SelectFapiaoNo(WebDriverWait wait) {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"Fapiao_YesNo\"]/label[2]/span")))
				.click();

	}

	public static Boolean LoadingAnimationComplete(WebDriverWait wait) {
		boolean LoadingOverlayClosed = wait
				.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loading_overlay")));
		return LoadingOverlayClosed;
	}

	public static void AcceptTermAndCond(WebDriverWait wait) {
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[@id=\"termAndCond_Wraper\"]/div/div/div/label/span")))
				.click();
	}

	public static void ClickOnPay(WebDriverWait wait) throws Exception {
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[@id=\"CheckoutOrderTotal\"]/div[4]/button"))).click();
		ApplicationLog.InfoLog("Clicked on Pay button");

	}

	public static void selectPaymentOption(WebDriverWait wait) throws IOException {
		try {
			String paymentOptionNameString = wait
					.until(ExpectedConditions.visibilityOfElementLocated(
							By.xpath("//*[@id=\"main__content--container\"]/div/div[4]/div/div/div/ul/form[2]/li")))
					.getText();
			ApplicationLog.InfoLog("Selected payment method : " + paymentOptionNameString);
			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//*[@id=\"main__content--container\"]/div/div[4]/div/div/div/ul/form[2]/li"))).click();

		} catch (Exception e) {
			ApplicationLog.InfoLog("Payment option is not avaliable, it has moved to GTS soft block");
		}

	}

	public static void ClickOnSuccessAlert(WebDriver driver) throws InterruptedException, IOException {

		while (true) {
			try {
				String paymentmessageString = driver.switchTo().alert().getText();
				ApplicationLog.InfoLog(paymentmessageString);

				driver.switchTo().alert().accept();
				break;
			} catch (NoAlertPresentException e) {

				Thread.sleep(700);
				continue;
			}
		}
	}

	public static String CaptureOrderNumber(WebDriver driver, WebDriverWait wait) {
		
		String OrderNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[@id=\"main__content--container\"]/div/div/div/div/div[1]/div/div[1]/ul/li[1]/div[2]/h4"))).getText();
		return OrderNumber;
	}

}

package CATChinaRetail.TestAutomation.Core;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class GenerateReport {
	
	public static ExtentHtmlReporter reporter = new ExtentHtmlReporter("C:\\Logs\\report.html");
	public static ExtentReports extent = new ExtentReports();
	
	
	public static void Pass()
	{
		reporter.loadXMLConfig("./src/main/resources/ExtentConfig.xml");
		String callerClassName = Thread.currentThread().getStackTrace()[2].getMethodName();
		extent.attachReporter(reporter);
		ExtentTest loggerPass = extent.createTest(callerClassName);
		loggerPass.info(MarkupHelper.createLabel(System.getProperty("os.name"), ExtentColor.BLUE));
		String comment = "The test case " + callerClassName + " is pass";
		loggerPass.log(Status.PASS, comment);
		extent.flush();
	}
	
	public static void Fail(WebDriver driver) throws IOException, HeadlessException, AWTException, InterruptedException
	{
		reporter.loadXMLConfig("./src/main/resources/ExtentConfig.xml");
		String callerClassName = Thread.currentThread().getStackTrace()[2].getMethodName();
		extent.attachReporter(reporter);
		ExtentTest loggerFail = extent.createTest(callerClassName);
		loggerFail.info(MarkupHelper.createLabel(System.getProperty("os.name"), ExtentColor.BLUE));
		String comment = "The test case " + callerClassName + " is failed";
		loggerFail.log(Status.FAIL, comment);
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH_mm_ss");
		Date date = new Date();
		String FileName = "Failure screenshot " + dateFormat.format(date) + ".png";
		String Path = "C:\\Logs\\" + FileName;
		
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File(Path));
		} catch (IOException e) {
			ApplicationLog.ErrorLog("Unable to capture screenshot");
			e.printStackTrace();
		}
		Thread.sleep(3000);
		loggerFail.log(Status.FAIL, "Snapshot below: " + loggerFail.addScreenCaptureFromPath(FileName));
		extent.flush();
	}
	
}

package CATChinaRetail.TestAutomation.Core;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public class ApplicationLog {

	static DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	static Date date = new Date();
	static String FileName = "Test Log for " + dateFormat.format(date) + ".log";
	static String Path = "C:\\Logs\\" + FileName;

	public static void InfoLog(String Message) throws IOException {

		File file = new java.io.File(Path);
		file.getParentFile().mkdirs();
		if (!file.exists()) {
			file.createNewFile();
		}

		System.setProperty("log_dir", Path);
		Logger Log = Logger.getLogger(ApplicationLog.class.getName());
		Log.info(Message);

	}
	
	public static void DebugLog(String Message) throws IOException {

		File file = new java.io.File(Path);
		file.getParentFile().mkdirs();
		if (!file.exists()) {
			file.createNewFile();
		}

		System.setProperty("log_dir", Path);
		Logger Log = Logger.getLogger(ApplicationLog.class.getName());
		Log.debug(Message);

	}
	
	public static void WarnLog(String Message) throws IOException {

		File file = new java.io.File(Path);
		file.getParentFile().mkdirs();
		if (!file.exists()) {
			file.createNewFile();
		}

		System.setProperty("log_dir", Path);
		Logger Log = Logger.getLogger(ApplicationLog.class.getName());
		Log.warn(Message);

	}
	
	public static void ErrorLog(String Message) throws IOException {

		File file = new java.io.File(Path);
		file.getParentFile().mkdirs();
		if (!file.exists()) {
			file.createNewFile();
		}

		System.setProperty("log_dir", Path);
		Logger Log = Logger.getLogger(ApplicationLog.class.getName());
		Log.error(Message);

	}
	
	public static void FatalLog(String Message) throws IOException {

		File file = new java.io.File(Path);
		file.getParentFile().mkdirs();
		if (!file.exists()) {
			file.createNewFile();
		}

		System.setProperty("log_dir", Path);
		Logger Log = Logger.getLogger(ApplicationLog.class.getName());
		Log.fatal(Message);

	}

}

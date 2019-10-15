package CATChinaRetail.TestAutomation.Drivers;

public class DriverManagerFactory {

	public static DriverManager getDriverManager(DriverType type) {
		DriverManager driverManager;
		switch (type) {
		case CHROME:
			driverManager = new ChromeDriverManager();

			break;
		default:
			driverManager = new ChromeDriverManager();

			break;

		}
		return driverManager;
	}

}

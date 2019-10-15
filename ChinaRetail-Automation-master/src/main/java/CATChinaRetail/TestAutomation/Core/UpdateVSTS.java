package CATChinaRetail.TestAutomation.Core;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

import org.apache.commons.lang3.StringUtils;

public class UpdateVSTS {
	
	static String GetTestPointID(String VSTSTestID) throws Exception
	
	{
		ApplicationLog.InfoLog("Sending Http GET request to get details of the test case");
		URL url = new URL(
				"https://dev.azure.com/ecommercedev-cat-com/China%20Retail/_apis/test/Plans/21/Suites/1987/points?testCaseId="+VSTSTestID+"&api-version=5.0");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		// Add Request header

		String encoding = Base64.getEncoder()
				.encodeToString("mishrs11:asfegz2qa7q6kypni6rktrxwek6fg2mif2cj47ovcj32eq6baaza".getBytes("utf-8"));
		con.setRequestMethod("GET");
		con.setRequestProperty("Authorization", "Basic " + encoding);
		int responseCode = con.getResponseCode();
		
		ApplicationLog.InfoLog("Sending 'GET' request to URL : " + url);
		ApplicationLog.InfoLog("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// print result
		String TestPointID = null;
		String responseMessageString = null;
		responseMessageString = response.toString();
		TestPointID = StringUtils.substringBetween(responseMessageString, "\"id\":", ",\"url\"");
		ApplicationLog.InfoLog("The Test Point ID is : " + TestPointID);
		
		return TestPointID;
	}

	public static void PassTestCase(String VSTSTestID) throws Exception {

		String TestPointID = GetTestPointID(VSTSTestID);
		ApplicationLog.InfoLog("Sending Http POST request for Passed test case");
		URL url = new URL(
				"https://dev.azure.com/ecommercedev-cat-com/China%20Retail/_api/_testManagement/BulkMarkTestPoints");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		// Add Request header

		String encoding = Base64.getEncoder()
				.encodeToString("mishrs11:asfegz2qa7q6kypni6rktrxwek6fg2mif2cj47ovcj32eq6baaza".getBytes("utf-8"));
		con.setRequestMethod("POST");
		con.setRequestProperty("Authorization", "Basic " + encoding);
		con.setRequestProperty("Content-Type", "application/json-patch+json");

		String urlParameters = "{\"planId\":21,\"suiteId\":1987,\"testPointIds\":["+TestPointID+"],\"outcome\":2}";

		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		ApplicationLog.InfoLog("Sending 'POST' request to URL : " + url);
		ApplicationLog.InfoLog("Post parameters : " + urlParameters);
		ApplicationLog.InfoLog("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// print result
		ApplicationLog.InfoLog("Updated VSTS Successfully");

	}

	public static void FailTestCase(String VSTSTestID) throws Exception {
		
		String TestPointID = GetTestPointID(VSTSTestID);
		ApplicationLog.InfoLog("Send Http POST request for Failed test case");
		URL url = new URL(
				"https://dev.azure.com/ecommercedev-cat-com/China%20Retail/_api/_testManagement/BulkMarkTestPoints");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		// Add Request header

		String encoding = Base64.getEncoder()
				.encodeToString("mishrs11:asfegz2qa7q6kypni6rktrxwek6fg2mif2cj47ovcj32eq6baaza".getBytes("utf-8"));
		con.setRequestMethod("POST");
		con.setRequestProperty("Authorization", "Basic " + encoding);
		con.setRequestProperty("Content-Type", "application/json-patch+json");

		String urlParameters = "{\"planId\":21,\"suiteId\":1987,\"testPointIds\":["+TestPointID+"],\"outcome\":3}";

		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		ApplicationLog.InfoLog("Sending 'POST' request to URL : " + url);
		ApplicationLog.InfoLog("Post parameters : " + urlParameters);
		ApplicationLog.InfoLog("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// print result
		ApplicationLog.InfoLog("Updated VSTS Successfully");


	}

}

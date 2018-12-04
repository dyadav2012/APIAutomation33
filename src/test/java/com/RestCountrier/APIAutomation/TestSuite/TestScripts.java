package com.RestCountrier.APIAutomation.TestSuite;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.RestCountrier.APIAutomation.Utilities.ApiResponse;
import com.RestCountrier.APIAutomation.Utilities.ExcelUtilities;
import com.RestCountrier.APIAutomation.Utilities.ProcessApiRequest;

/*
 * 
 * Using separate data provider to get test data for each of end Point and this data provider is connected with different test case related to that end point which read test data from corrasponding end point test data sheet and process request and validates with expected response and status code.
 *  
 * We are hard coding api response for each of endPoint url value and then
 * sending request and validating with actual if matches then pass else
 * fail. Will create a report html also apart from this excel as report
 * 
 * Will make Test will fail whether actual JSONresult is not matching with
 * hard coded expected into excel or status code is not 200
 * 
 * Therefore printing status code also.
 * 
 * Using org.json to switch between jsonObject and java object
 * 
 * Below all are get API, in case of PUT/POST/DEL we need to write corrasponding utility methods for each and then call from tests as per requirements.
 * 
 */

public class TestScripts {

	static int rowNum;

	// Generic Url string.

	static String url = "https://restcountries.eu//rest//v2//";

	static ApiResponse apiResponse;

	@DataProvider(name = "Full Name")

	public static Object[][] getTestDataForAllEndPoint() {

		// Variable defined to print values into excel sheet.

		rowNum = 1;

		// returning test data from Full Name End Point

		return ExcelUtilities.readTestData("Full Name");
	}

	@Test(dataProvider = "Full Name")
	public void testEndPoiuntAll(String endPoint, String expectedResult, String actualResult, String statusCode,
			String result) {

		String localUrl = url + "name" + endPoint + "//?fullText=true";

		// Collected API response for Get Request with endPoint Full Name

		apiResponse = ProcessApiRequest.GetRequest(localUrl);

		// writing into Result sheet

		// Writing actual Result

		actualResult = apiResponse.getResponse();

		ExcelUtilities.writeResult("Full Name", 2, rowNum, actualResult);

		// Writing status Code

		statusCode = apiResponse.getstatusCode().toString();

		ExcelUtilities.writeResult("Full Name", 3, rowNum, statusCode);

		Assert.assertEquals(expectedResult, apiResponse.getResponse());

		if ((expectedResult.equals(actualResult)) && (statusCode.equals("200"))) {
			ExcelUtilities.writeResult("Full Name", 4, rowNum, "Pass");
		} else {
			ExcelUtilities.writeResult("Full Name", 4, rowNum, "Fail");
		}

		// Increasing for next test data execution of this end point.

		rowNum++;

	}

}
package com.RestCountrier.APIAutomation.Utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

public class ProcessApiRequest {

	private static ApiResponse apiResponse;

	public static ApiResponse GetRequest(String url) {

		try {
			// Defining http client to process request

			HttpClient client = HttpClientBuilder.create().build();

			// Defining http get request to process api request

			HttpGet request = new HttpGet(url);

			// add request header
			request.setHeader("Accept", "application/json");

			HttpResponse response = client.execute(request);

			// Reading response in bytes

			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}

			// returning the api response object

			apiResponse = new ApiResponse(line, response.getStatusLine().getStatusCode());
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return apiResponse;
	}
}

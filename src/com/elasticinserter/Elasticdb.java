package com.elasticinserter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

public class Elasticdb {


	public void insertIntoElastic(String jsonInput) {

		try {
			String username="elasticusername";
			String password="elasticpassword";
			URL url = new URL("url");
			String encoding = Base64.getEncoder().encodeToString((username+":"+password).getBytes());

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestProperty("Authorization", "Basic " + encoding);
			connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

            OutputStream os = connection.getOutputStream();
            os.write(jsonInput.getBytes("UTF-8"));
            System.out.println("processing "+jsonInput.getBytes("UTF-8"));
            os.flush();
            
            if (connection.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
                throw new RuntimeException("Failed : HTTP error code : "
                    + connection.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (connection.getInputStream())));
            
            String output;
            System.out.println("Output from Server\n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }

            connection.disconnect();
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
		}
	}

}

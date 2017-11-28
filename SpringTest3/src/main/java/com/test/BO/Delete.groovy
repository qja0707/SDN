package com.test.BO;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

public class Delete {

//	private static final String ENCODING = "UTF-8";

	/**
	 * @param args
	 */
	public Delete(String switchName) {

        try {
            URL url = new URL ("http://localhost:8181/restconf/config/opendaylight-inventory:nodes/node/openflow:${switchName}");
            String encoding = Base64.getEncoder().encodeToString(("admin:admin").getBytes("UTF-8"));

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");
            connection.setDoOutput(true);
            connection.setRequestProperty  ("Authorization", "Basic " + encoding);
            InputStream content = (InputStream)connection.getInputStream();
            BufferedReader input   = 
                new BufferedReader (new InputStreamReader (content));
            String line;
            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

    }
}

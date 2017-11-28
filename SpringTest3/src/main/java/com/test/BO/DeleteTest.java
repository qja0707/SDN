package com.test.BO;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

public class DeleteTest {

//	private static final String ENCODING = "UTF-8";

	/**
	 * @param args
	 */
	public static void main(String[] args) {

        try {
            URL url = new URL ("http://localhost:8181/restconf/config/opendaylight-inventory:nodes/node/openflow:4/table/0/flow/1234");
            String encoding = Base64.getEncoder().encodeToString(("admin:admin").getBytes("UTF-8"));

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");
            connection.setDoOutput(true);
            connection.setRequestProperty  ("Authorization", "Basic " + encoding);
            InputStream content = (InputStream)connection.getInputStream();
            BufferedReader in   = 
                new BufferedReader (new InputStreamReader (content));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

    }
}

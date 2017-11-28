package com.test.BO;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

public class GetTest {

//	private static final String ENCODING = "UTF-8";

	private String line;
	
	public GetTest(String switchNum) {

        try {
        	line="";
            URL url = new URL ("http://localhost:8181/restconf/config/opendaylight-inventory:nodes/node/openflow:"+switchNum);
            String encoding = Base64.getEncoder().encodeToString(("admin:admin").getBytes("UTF-8"));

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setRequestProperty  ("Authorization", "Basic " + encoding);
            InputStream content = (InputStream)connection.getInputStream();
            BufferedReader in   = 
                new BufferedReader (new InputStreamReader (content));
            line="";
            String temp;
            while ((temp=in.readLine()) != null) {
                line=line+temp;
                System.out.println(temp);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

	public String getLine() {
		return line;
	}
	
}

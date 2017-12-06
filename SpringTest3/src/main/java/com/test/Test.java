package com.test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Test {

	public static void main(String[] args) {
		String line=null;
		JSONObject jobj = null;
		JSONArray msg = null;
		//JSONArray msgt = null;
		//JSONArray[][] JAA = null;
		System.out.println("topoParsing start");
		try {
			line = "";
			URL url = new URL(
					"http://52.231.25.158:"+"18182"+"/restconf/operational/network-topology:network-topology/topology/flow:1");
			String encoding = Base64.getEncoder().encodeToString(("admin:admin").getBytes("UTF-8"));

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoOutput(true);
			connection.setRequestProperty("Authorization", "Basic " + encoding);
			InputStream content = (InputStream) connection.getInputStream();

			BufferedReader in = new BufferedReader(new InputStreamReader(content));
			line = "";
			String temp=null;
			while ((temp = in.readLine()) != null) {
				line = line + temp;
				System.out.println(temp);
			}
//			System.out.println(line);
			JSONParser parser = new JSONParser();
			jobj = (JSONObject) parser.parse(line);
			msg = (JSONArray) jobj.get("topology");
			jobj = (JSONObject) msg.get(0);
//			msg = (JSONArray) jobj.get("link");
			//int i=0;
			//i = msg.size();
			//System.out.println("i : "+i);
			//JAA = new JSONArray[2][i];

			/*
			for(int j=0; j<i;j++) {
				jobj = (JSONObject) msg.get(j);
				 JAA[0][j].add((JSONArray) jobj.get("link-id"));
				msgt = (JSONArray) jobj.get("destination");
				jobj = (JSONObject) msgt.get(0);
				JAA[1][j].add((JSONArray) jobj.get("dest-tp"));
			
			}
			*/
			//System.out.println("msg:"+jobj);

			
		} catch (Exception e) {
			e.printStackTrace();
			String temp = "[{\"Server is not running, please wait\":0}]";
			JSONParser parser = new JSONParser();
			try {
				msg = (JSONArray) parser.parse(temp);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.out.println("temp empty parse error");
			}
		}
		
		System.out.println(msg);
	}

}

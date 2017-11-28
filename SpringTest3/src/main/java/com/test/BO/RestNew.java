package com.test.BO;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

import com.test.VO.Xmls;

public class RestNew {
	Xmls xmls;
	HttpURLConnection connection;
	String switchName;
	String flowId;
	String charset;
	String encoding;
	URL groupUrl;
	URL flowUrl;
	
	public RestNew(String []swAndHost){
		xmls = new Xmls(swAndHost);
		charset = "UTF-8";

		try {
			groupUrl = new URL (xmls.getGroupUrl());
			flowUrl = new URL (xmls.getFlowUrl());
			encoding = Base64.getEncoder().encodeToString(("admin:admin").getBytes("UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("xmls class error");
		}
	}
	public void groupPut(){
		try {
			connection = (HttpURLConnection) groupUrl.openConnection();
			connection.setRequestProperty  ("Authorization", "Basic " + encoding);
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "application/xml");
			connection.setRequestProperty("Accept", "application/xml");
			connection.setRequestMethod("PUT");
			OutputStreamWriter out = new OutputStreamWriter(
					connection.getOutputStream(),charset);
			out.write(xmls.getGroupXml());
			out.close();
			
			InputStream content = (InputStream)connection.getInputStream();
			BufferedReader input   =
					new BufferedReader (new InputStreamReader (content));
			String line;
			while ((line = input.readLine()) != null) {
				System.out.println(line);
			}
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("grouping error");
		}finally{
			connection.disconnect();
		}
	}
	public void flowPut(){
		
		try {
			connection = (HttpURLConnection) flowUrl.openConnection();
			connection.setRequestProperty  ("Authorization", "Basic " + encoding);
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "application/xml");
			connection.setRequestProperty("Accept", "application/xml");
			connection.setRequestMethod("PUT");
			OutputStreamWriter out = new OutputStreamWriter(
			connection.getOutputStream(),charset);
			out.write(xmls.getFlowXml());
			out.close();

			InputStream content = (InputStream)connection.getInputStream();
			BufferedReader input   =
			new BufferedReader (new InputStreamReader (content));
			String line;
			while ((line = input.readLine()) != null) {
				System.out.println(line);
			}
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("flow put error");
		}finally{
			connection.disconnect();
		}
	}
	public void restDelete(){
		try{
			connection = (HttpURLConnection) flowUrl.openConnection();
			connection.setRequestProperty  ("Authorization", "Basic " + encoding);
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "application/xml");
			connection.setRequestProperty("Accept", "application/xml");
			connection.setRequestMethod("DELETE");
			InputStream content = (InputStream)connection.getInputStream();
			BufferedReader read   =
			new BufferedReader (new InputStreamReader (content));
			String line;
			while ((line = read.readLine()) != null) {
				System.out.println(line);
			}
		}catch(Exception e){
			System.out.println("there is no flow");
		}finally{
			connection.disconnect();
		}
		
		try{
			connection = (HttpURLConnection) groupUrl.openConnection();
			connection.setRequestProperty  ("Authorization", "Basic " + encoding);
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "application/xml");
			connection.setRequestProperty("Accept", "application/xml");
			connection.setRequestMethod("DELETE");
			InputStream content = (InputStream)connection.getInputStream();
			BufferedReader read   =
			new BufferedReader (new InputStreamReader (content));
			String line;
			while ((line = read.readLine()) != null) {
				System.out.println(line);
			}
		}catch(Exception e){
			System.out.println("there is no group");
		}finally{
			connection.disconnect();
		}
	}
}

package com.test.BO;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.test.VO.FlowTable;

public class GetAndParser {

	// private static final String ENCODING = "UTF-8";

	public JSONArray flowParsing(String switchName) {
		String line;
		JSONObject jobj = null;
		JSONArray msg = null;
		try {
			line = "";
			URL url = new URL(
					"http://localhost:8181/restconf/config/opendaylight-inventory:nodes/node/openflow:" + switchName);
			String encoding = Base64.getEncoder().encodeToString(("admin:admin").getBytes("UTF-8"));

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoOutput(true);
			connection.setRequestProperty("Authorization", "Basic " + encoding);
			InputStream content = (InputStream) connection.getInputStream();

			BufferedReader in = new BufferedReader(new InputStreamReader(content));
			line = "";
			String temp;
			while ((temp = in.readLine()) != null) {
				line = line + temp;
				System.out.println(temp);
			}
			JSONParser parser = new JSONParser();
			jobj = (JSONObject) parser.parse(line);

			msg = (JSONArray) jobj.get("node");
			System.out.println("aaaaa: " + msg.get(0));
			jobj = (JSONObject) msg.get(0);
			System.out.println(jobj.get("flow-node-inventory:table"));

			msg = (JSONArray) jobj.get("flow-node-inventory:table");
			System.out.println(msg.get(0));

			jobj = (JSONObject) msg.get(0);
			System.out.println(jobj.get("flow"));
			msg = (JSONArray) jobj.get("flow");
			System.out.println(msg.get(0));

			jobj = (JSONObject) msg.get(0);
			jobj = (JSONObject) jobj.get("instructions");
			System.out.println(jobj.get("instruction"));
			msg = (JSONArray) jobj.get("instruction");

			jobj = (JSONObject) msg.get(0);
			System.out.println(jobj.get("apply-actions"));
			jobj = (JSONObject) jobj.get("apply-actions");
			System.out.println(jobj.get("action"));
			msg = (JSONArray) jobj.get("action");

		} catch (Exception e) {
			e.printStackTrace();
			String temp = "[{\"empty\":0}]";
			JSONParser parser = new JSONParser();
			try {
				msg = (JSONArray) parser.parse(temp);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.out.println("temp empty parse error");
			}
		}
		return msg;
	}

	public JSONArray groupParsing(String switchName) {
		String line;
		JSONObject jobj = null;
		JSONArray msg = null;
		try {
			line = "";
			URL url = new URL(
					"http://localhost:8181/restconf/config/opendaylight-inventory:nodes/node/openflow:" + switchName);
			String encoding = Base64.getEncoder().encodeToString(("admin:admin").getBytes("UTF-8"));

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoOutput(true);
			connection.setRequestProperty("Authorization", "Basic " + encoding);
			InputStream content = (InputStream) connection.getInputStream();

			BufferedReader in = new BufferedReader(new InputStreamReader(content));
			line = "";
			String temp;
			while ((temp = in.readLine()) != null) {
				line = line + temp;
				System.out.println(temp);
			}
			JSONParser parser = new JSONParser();
			jobj = (JSONObject) parser.parse(line);

			msg = (JSONArray) jobj.get("node");
			System.out.println("aaaaa: " + msg.get(0));
			jobj = (JSONObject) msg.get(0);
			System.out.println("bbb: " + jobj.get("flow-node-inventory:group"));

			msg = (JSONArray) jobj.get("flow-node-inventory:group");
			System.out.println(msg.get(0));

			jobj = (JSONObject) msg.get(0);
			System.out.println(jobj.get("buckets"));
			jobj = (JSONObject) jobj.get("buckets");
			msg = (JSONArray) jobj.get("bucket");
			System.out.println(msg.get(0));
			System.out.println(msg.get(1));

			JSONObject jobj2 = new JSONObject();
			JSONArray msg2 = new JSONArray();

			jobj = (JSONObject) msg.get(0);
			jobj2 = (JSONObject) msg.get(1);

			msg = (JSONArray) jobj.get("action");
			jobj = (JSONObject) msg.get(0);
			jobj = (JSONObject) jobj.get("output-action");

			msg2 = (JSONArray) jobj2.get("action");
			jobj2 = (JSONObject) msg2.get(0);
			jobj2 = (JSONObject) jobj2.get("output-action");

			msg.clear();
			msg.add(jobj);
			msg.add(jobj2);
			System.out.println(jobj);
			System.out.println(jobj2);

		} catch (Exception e) {
			e.printStackTrace();
			String temp = "[{\"empty\":0}]";
			JSONParser parser = new JSONParser();
			try {
				msg = (JSONArray) parser.parse(temp);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.out.println("temp empty parse error");
			}
		}
		return msg;
	}

	// public static void main(String[] args) {
	public String totalParse() {
		ArrayList list = new ArrayList();
		GetAndParser g = new GetAndParser();

		FlowTable f3 = new FlowTable();
		f3.setSwitchName("openflow3");
		f3.setFlows(g.flowParsing("3"));
		f3.setGroup(g.groupParsing("3"));

		FlowTable f2 = new FlowTable();
		f2.setSwitchName("openflow2");
		f2.setFlows(g.flowParsing("2"));
		f2.setGroup(g.groupParsing("2"));

		FlowTable f1 = new FlowTable();
		f1.setSwitchName("openflow1");
		f1.setFlows(g.flowParsing("1"));
		f1.setGroup(g.groupParsing("1"));

		list.add(f1);
		list.add(f2);
		list.add(f3);

		String output = new Gson().toJson(list);

		System.out.println(output);
		return output;
	}

	/*
	 * public String getLine() { return line; }
	 */

}

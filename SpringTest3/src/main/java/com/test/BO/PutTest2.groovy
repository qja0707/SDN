package com.test.BO

import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import java.util.Base64

class PutTest2 {
	public PutTest2(String[] swFlowIdOut) {

		String switchName = swFlowIdOut[0];
		String flowId = swFlowIdOut[1];
		String outputHost;
		
		String xml = """<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<flow xmlns="urn:opendaylight:flow:inventory">
    <priority>3</priority>
    <flow-name>0</flow-name>
    <match>
        <ethernet-match>
            <ethernet-type>
                <type>2048</type>
            </ethernet-type>
        </ethernet-match>
    </match>
    <id>${flowId}</id>
    <table_id>0</table_id>
    <instructions>
        <instruction>
            <order>0</order>
            <apply-actions>
                <action>
                   <order>0</order>
                    <output-action>
                        <output-node-connector>${swFlowIdOut[2]}</output-node-connector>
                        <max-length>60</max-length>
                    </output-action>
                    
                </action>
            </apply-actions>
        </instruction>
    </instructions>
</flow>
"""
		
		
		try {
			String charset = "UTF-8"
			String encoding = Base64.getEncoder().encodeToString(("admin:admin").getBytes("UTF-8"));
			URL url = new URL("http://localhost:8181/restconf/config/opendaylight-inventory:nodes/node/openflow:${switchName}/table/0/flow/${flowId}");
			HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
			httpCon.setDoOutput(true);
			httpCon.setDoInput(true);
			httpCon.setRequestMethod("PUT");
			httpCon.setRequestProperty  ("Authorization", "Basic " + encoding);
			httpCon.setRequestProperty("Content-Type", "application/xml");
			httpCon.setRequestProperty("Accept", "application/xml");
			OutputStreamWriter out = new OutputStreamWriter(
			httpCon.getOutputStream(),charset);
			out.write(xml);
			out.close();

			InputStream content = (InputStream)httpCon.getInputStream();
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

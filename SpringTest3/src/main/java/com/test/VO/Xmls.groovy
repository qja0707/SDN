package com.test.VO

class Xmls {
	private String server="localhost";
	private String serverPort="8181";
	private String groupUrl;
	private String flowUrl;
	private String flowId=1;
	private String groupId=1;
	private String tableId=0;
	private String destination = "239.0.0.1/24";
	private String charset = "UTF-8";

	private String bucket="";
	private String groupXml;
	private String flowXml="""<?xml version="1.0" encoding="${charset}" standalone="no"?>
<flow xmlns="urn:opendaylight:flow:inventory">
    <strict>false</strict>
    <hard-timeout>0</hard-timeout>
    <idle-timeout>0</idle-timeout>
    <instructions>
        <instruction>
            <order>0</order>
            <apply-actions>
                <action>
                    <group-action>
                        <group-id>${groupId}</group-id>
                    </group-action>
                    <order>1</order>
                </action>
            </apply-actions>
        </instruction>
    </instructions>
    <table_id>${tableId}</table_id>
    <id>${flowId}</id>
    <match>
        <ethernet-match>
            <ethernet-type>
                <type>2048</type>
            </ethernet-type>
        </ethernet-match>
        <ipv4-destination>${destination}</ipv4-destination>
    </match>
    <flow-name>FlowWithGroupInstruction</flow-name>
    <priority>3</priority>
</flow>
"""

	public Xmls(String []swAndHost){
		groupUrl = "http://${server}:${serverPort}/restconf/config/opendaylight-inventory:nodes/node/openflow:${swAndHost[0]}/group/${groupId}";
		flowUrl = "http://${server}:${serverPort}/restconf/config/opendaylight-inventory:nodes/node/openflow:${swAndHost[0]}/table/${tableId}/flow/${flowId}";
		for(int i=1;i<swAndHost.length;i++){
			bucket+="""<bucket>
         <weight>1</weight>
            <action>
                <output-action>
                    <output-node-connector>${swAndHost[i]}</output-node-connector>
                </output-action>
                <order>1</order>
            </action>
            <bucket-id>${i}</bucket-id>
        </bucket>
"""
		}
		groupXml="""<?xml version="1.0" encoding="${charset}" standalone="no"?>
				<group xmlns="urn:opendaylight:flow:inventory">
				<group-type>group-all</group-type>
				<buckets>
				${bucket}
				</buckets>
				<barrier>false</barrier>
				<group-name>SelectGroup</group-name>
				<group-id>${groupId}</group-id>
				</group>
				"""
	}

	public String getServer() {
		return server;
	}

	public String getServerPort() {
		return serverPort;
	}

	public String getGroupUrl() {
		return groupUrl;
	}

	public String getFlowUrl() {
		return flowUrl;
	}

	public String getFlowId() {
		return flowId;
	}

	public String getGroupId() {
		return groupId;
	}

	public String getTableId() {
		return tableId;
	}

	public String getDestination() {
		return destination;
	}

	public String getCharset() {
		return charset;
	}

	public String getBucket() {
		return bucket;
	}

	public String getGroupXml() {
		return groupXml;
	}

	public String getFlowXml() {
		return flowXml;
	}

	public void setGroupUrl(String groupUrl) {
		this.groupUrl = groupUrl;
	}

	public void setFlowUrl(String flowUrl) {
		this.flowUrl = flowUrl;
	}
	
}

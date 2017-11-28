package com.test.VO;

import org.json.simple.JSONArray;

public class FlowTable {
	private String switchName;
	private JSONArray group;
	private JSONArray flows;
	public String getSwitchName() {
		return switchName;
	}
	public void setSwitchName(String switchName) {
		this.switchName = switchName;
	}
	public JSONArray getGroup() {
		return group;
	}
	public void setGroup(JSONArray group) {
		this.group = group;
	}
	public JSONArray getFlows() {
		return flows;
	}
	public void setFlows(JSONArray flows) {
		this.flows = flows;
	}
	
}

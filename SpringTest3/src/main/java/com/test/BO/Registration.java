package com.test.BO;

import com.test.DAO.DBconnector;
import com.test.VO.Member;

public class Registration {
	Member member=null;
	
	public Registration(Member member){
		this.member=member;
		
		System.out.println("registration class is running");
		
//		DBconnector dbConnector = new DBconnector();
//		dbConnector.regId(member);
//		dbConnector.regPort(member);
		
//		MakeContainer mc = new MakeContainer(member);
		MakeContainer mc = new MakeContainer();
		mc.executeCommand("sudo docker run -d -p 16630:6633 -p 18180:8181 ubuntu:odl2 /distribution-karaf-0.6.1-Carbon/bin/karaf");
		
//		dbConnector.disconnect();
	}
}

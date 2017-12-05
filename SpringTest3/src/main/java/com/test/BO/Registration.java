package com.test.BO;

import com.test.DAO.DBconnector;
import com.test.VO.Member;

public class Registration {
	Member member=null;
	
	public Registration(Member member){
		this.member=member;
		
		System.out.println("registration class is running");
		
		DBconnector dbConnector = new DBconnector();
		dbConnector.regId(member);
		this.member=dbConnector.regPort(member);
		
		MakeContainer mc = new MakeContainer();
		mc.makeContainer(member);
		
		dbConnector.disconnect();
	}
}

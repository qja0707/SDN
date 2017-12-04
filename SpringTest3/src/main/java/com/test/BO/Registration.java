package com.test.BO;

import com.test.DAO.DBconnector;
import com.test.VO.Member;

public class Registration {
	Member member=null;
	
	public Registration(Member member){
		this.member=member;
		
		DBconnector dbConnector = new DBconnector();
		dbConnector.regId(member);
		dbConnector.regPort(member);
		
		MakeContainer mc = new MakeContainer(member);
		
		dbConnector.disconnect();
	}
}

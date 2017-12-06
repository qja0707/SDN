package com.test.BO;

import com.test.DAO.DBconnector;
import com.test.VO.Member;

public class SignIn {
	private Member member = new Member();
	public SignIn(Member member){
		this.member=member;
		
		System.out.println("signin class is running");
		
		DBconnector dbConnector = new DBconnector();
		this.member=dbConnector.signin(member);
		
		System.out.println("member port:"+member.getPort8181());
		dbConnector.disconnect();
		System.out.println("after disconnect");
		
	}
	public Member getMember() {
		return member;
	}
	
}

package com.test.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.test.VO.Member;

public class DBconnector {
	Connection connection = null;
	Statement st = null;
	PreparedStatement pstmt = null;
	
	public DBconnector(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://ja-cdbr-azure-west-a.cloudapp.net:3306/sdn", "b4484a12c122e1", "46df1f90");
			st = connection.createStatement();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Member regPort(Member member){
		int port6633 = 0;
		int port8181 = 0;
		
		String sql = "select max(port8181) as port8181,max(port6633) as port6633 from portnum;";
		try {
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				port6633 = rs.getInt("port6633")+1;
				port8181 = rs.getInt("port8181")+1;
			}
			if(port6633<10000)
				port6633=16630;
			if(port8181<10000)
				port8181=18180;
			member.setPort6633(port6633);
			member.setPort8181(port8181);
			sql = "insert into portnum (port6633,port8181,id) values(?,?,?);";
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1,port6633);
			pstmt.setInt(2, port8181);
			pstmt.setString(3, member.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return member;
	}
	
	public void regId(Member member){
		String sql = "insert into member values(?,?);";
		
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPw());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void disconnect(){
		try {
			if(!pstmt.isClosed()){
				pstmt.close();
			}
			if(!st.isClosed()){
				st.close();
			}
			if(!connection.isClosed()){
				connection.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

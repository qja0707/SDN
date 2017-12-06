package com.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.test.VO.Member;

public class Test {

	public static void main(String[] args) {
		Connection connection = null;
		Statement st = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://ja-cdbr-azure-west-a.cloudapp.net:3306/sdn",
					"b4484a12c122e1", "46df1f90");
			st = connection.createStatement();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int port6633 = 0;
		int port8181 = 0;
		Member member = new Member();
		member.setId("test");
		member.setPw("test");

		String sql = "select port8181, port6633 from member, portnum where member.id=portnum.id and member.id=\'"
				+ member.getId() + "\' and member.pw=\'" + member.getPw() + "\';";
		try {
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				port6633 = rs.getInt("port6633") + 1;
				port8181 = rs.getInt("port8181") + 1;
			}
			// if(port6633<10000)
			// port6633=16630;
			// if(port8181<10000)
			// port8181=18180;
			member.setPort6633(port6633);
			member.setPort8181(port8181);
			
			System.out.println("port6633 : "+member.getPort6633());

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("There is no id or pw");
			member.setPort6633(port6633);
			member.setPort8181(port8181);
		}
	}

}

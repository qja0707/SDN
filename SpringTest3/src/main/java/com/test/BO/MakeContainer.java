package com.test.BO;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.test.VO.Member;

public class MakeContainer {

	public String makeContainer(Member member) {
		
		
		System.out.println("port:"+member.getPort6633());

		StringBuffer output = new StringBuffer();
		String command = "sudo docker run --rm -d -p " + member.getPort6633() + ":6633 -p " + member.getPort8181()
				+ ":8181 qja0707/odl /distribution-karaf-0.6.1-Carbon/bin/karaf";

		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

			String line = "";
			while ((line = reader.readLine()) != null) {
				output.append(line + "\n");
			}
			System.out.println(output);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return output.toString();
	}
}

package com.test.BO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.test.VO.Member;

public class MakeContainer {

	public void makeContainer(Member member) {
		String str;
		String command = "cmd.exe /c dir";
		String[] cmd = { "/bin/sh", "-c", "" };
		int port6633 = member.getPort6633();
		int port8181 = member.getPort8181();
		cmd[2] = cmd[2] + "sudo docker -d -p " + port6633 + " 6633 -p " + port8181 + " 8181 ubunt:odl2";
		try {
			Process proc = Runtime.getRuntime().exec(cmd);

			BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream()));

			while ((str = br.readLine()) != null) {
				System.out.println(str);
			}

			br.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String executeCommand(String command) {

		StringBuffer output = new StringBuffer();

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

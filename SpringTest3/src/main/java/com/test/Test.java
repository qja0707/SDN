package com.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test {

	public static void main(String[] args) {
		String str;
		String command="cmd.exe /c dir";
		String[] cmd = {"/bin/sh", "-c", "" };
		int port6633 = 16630;
		int port8181 = 18180;
		cmd[2]=cmd[2]+"sudo docker -d -p "+port6633+" 6633 -p "+port8181+" 8181 ubunt:odl2";
		try {
			Process proc = Runtime.getRuntime().exec(command);
			
			BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			
			while((str=br.readLine())!=null){
				System.out.println(str);
			}
			
			br.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

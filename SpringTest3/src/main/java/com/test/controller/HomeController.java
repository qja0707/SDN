package com.test.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.test.BO.AllDelete;
import com.test.BO.Delete;
import com.test.BO.GetAndParser;
import com.test.BO.PutTest2;
import com.test.BO.Registration;
import com.test.BO.RestNew;
import com.test.BO.SignIn;
import com.test.VO.Member;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	int switchNum = 3;
	String[] swAndHost = { "1", "2", "3" };

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "SignIn";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String registrationPage(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		return "Registration";
	}

	@RequestMapping(value = "/mainPage", method = RequestMethod.GET)
	public String mainPage(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "mainPage";
	}

	@RequestMapping(value = "/multiCast", method = RequestMethod.GET)
	public String multiCast(HttpServletRequest request) {

		RestNew forDelete = new RestNew(swAndHost);
		forDelete.restDelete();

		// rest.restDelete();

		String rawData = request.getParameter("jsonArr");
		System.out.println(request.getParameter("jsonArr"));

		try {
			rawData = rawData.replaceAll("\"", "");
			rawData = rawData.replaceAll("\\[", "");
			rawData = rawData.replaceAll("\\]", "");
			System.out.println(rawData);

			swAndHost = rawData.split(",");

			for (String string : swAndHost) {
				System.out.println("swAndHost: " + string);
			}

		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		// 플로우 다지우는거
		Delete delete = new Delete("1");
		System.out.println("delete switch 1");
		delete = new Delete("2");
		System.out.println("delete switch 2");
		delete = new Delete("3");
		System.out.println("delete switch 3");

		RestNew rest = new RestNew(swAndHost);
		rest.groupPut();
		rest.flowPut();

		// 원래 있던 플로우 없을때 대신하는 플로우
		String[] swFlowIdOut = new String[3];
		if (swAndHost[0].equals("1")) {
			swFlowIdOut[0] = "2";
			swFlowIdOut[1] = "0";
			swFlowIdOut[2] = "2";

			PutTest2 pt = new PutTest2(swFlowIdOut);
			swFlowIdOut[0] = "3";
			swFlowIdOut[2] = "3";
			pt = new PutTest2(swFlowIdOut);
			System.out.println("multicast on switch 1");
		} else if (swAndHost[0].equals("2")) {
			swFlowIdOut[0] = "1";
			swFlowIdOut[1] = "0";
			swFlowIdOut[2] = "2";

			PutTest2 pt = new PutTest2(swFlowIdOut);
			swFlowIdOut[0] = "3";
			swFlowIdOut[2] = "3";
			pt = new PutTest2(swFlowIdOut);
			System.out.println("multicast on switch 2");
		} else if (swAndHost[0].equals("3")) {
			swFlowIdOut[0] = "1";
			swFlowIdOut[1] = "0";
			swFlowIdOut[2] = "3";

			PutTest2 pt = new PutTest2(swFlowIdOut);
			swFlowIdOut[0] = "2";
			swFlowIdOut[2] = "2";
			pt = new PutTest2(swFlowIdOut);
			System.out.println("multicast on switch 3");
		}
		// rest.restPut(rawData);

		System.out.println("aaaa");
		return "restPut";
	}

	@RequestMapping(value = "/uniCast", method = RequestMethod.GET)
	public String uniCast(HttpServletRequest request) {
		for (int i = 0; i < switchNum; i++) {
			swAndHost[0] = "" + i;
			// RestNew forDelete = new RestNew(swAndHost);
			// forDelete.restDelete();
			AllDelete ad = new AllDelete();
		}
		return "restPut";
	}

	@RequestMapping(value = "/getXmls", method = RequestMethod.GET)
	public String getXmls(Model model) {
		GetAndParser gap = new GetAndParser();
		model.addAttribute("JSONArr", gap.totalParse());
		return "getXmls";
	}

	@RequestMapping(value = "/registration.do", method = RequestMethod.POST)
	public String registration(HttpServletRequest request) {

		System.out.println("controller-registration.do");
		Member member = new Member();

		member.setId(request.getParameter("id"));
		member.setPw(request.getParameter("pw"));

		System.out.println(member.getId() + "," + member.getPw());
		Registration reg = new Registration(member);
		return "SignIn";
	}

	@RequestMapping(value = "/signin.do", method = RequestMethod.POST)
	public String signin(HttpServletRequest request, Model model) {
		GetAndParser gap = new GetAndParser();
		
		Member member = new Member();

		member.setId(request.getParameter("id"));
		member.setPw(request.getParameter("pw"));
		System.out.println(member.getId() + "," + member.getPw());
		
		SignIn signin = new SignIn(member);
		member.setPort8181(signin.getMember().getPort8181());
		member.setPort6633(signin.getMember().getPort6633());
		
		JSONArray msg = null;
		//부적절한 포트면 로그인이 제대로 안된것이기때문에 아이디 패스워드가 잘못되었다는 메시지를.
		if(member.getPort6633()<10000){
			JSONParser parser = new JSONParser();
			String temp="[{\"Id or Password is unavailable. Please check your id or pw\":0}]";
			try {
				msg = (JSONArray) parser.parse(temp);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.out.println("temp empty parse error");
			}
		}
		//적절한 포트면 기다려달라는 메시지와 포트 번호를
		model.addAttribute("JSONArr", gap.topoParsing(member));
		
		String portInfo = "Controller ip: 52.231.25.158		port : "+member.getPort6633();
		model.addAttribute("Port", portInfo);
		System.out.println(portInfo);
		// System.out.println("num : " + JA.length);
		return "topologyXml";
	}
}

package org.hdcd.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.hdcd.vo.Address;
import org.hdcd.vo.Card;
import org.hdcd.vo.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class JSPHomeController {
	private static Logger logger = LoggerFactory.getLogger(JSPHomeController.class);
	
	/*
	 * 7장. JSP
	 * 
	 * 1. 지시자
	 */
	
	// 지시자 시작 페이지
	// 최초 home.jsp 페이지입니다.
	@RequestMapping(value="/jsp", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! is locale {}", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formatterDate = dateFormat.format(date);
		model.addAttribute("serverTime", formatterDate);
		return "home";
	}
	
	// 7장 1)지시자, 스크립트릿 내용 페이지
	@RequestMapping(value="/home01", method = RequestMethod.GET)
	public String home01(Locale locale, Model model) {
		logger.info("Welcome home! is locale {}", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formatterDate = dateFormat.format(date);
		model.addAttribute("serverTime", formatterDate);
		return "home/home01";
	}
	
	// 지시자 & 스크립트릿
	@RequestMapping(value="/home02", method = RequestMethod.GET)
	public String home02(Model model) {
		logger.info("home02");
		
		Member member = new Member();
		member.setUserId("hongkd");
		member.setPassword("1234");
		member.setEmail("aaa@ccc.com");
		member.setUserName("홍길동");
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2022);
		cal.set(Calendar.MONTH, 10);
		cal.set(Calendar.DAY_OF_MONTH, 7);
		member.setDateOfBirth(cal.getTime());
		model.addAttribute(member);
		return "home/home02";
	}
	
	// 3) includes/footer.jsp  includes/header.jsp
	@RequestMapping(value="/home03", method = RequestMethod.GET)
	public String home03(Locale locale, Model model) {
		logger.info("Welcome home! is locale {}", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formatterDate = dateFormat.format(date);
		model.addAttribute("serverTime", formatterDate);
		return "home/home03";
	}

	/*
	 * 3. 커스텀 태그 라이브러리
	 * 스크립트 요소가 많아지면 많아질수록 JSP 코드는 복잡해집니다.
	 * 이 문제를 해결하는 한 가지 방법은 커스텀 태그를 사용하는 것입니다.
	 * 커스텀 태그를 사용하면 뷰를 표시하기 위한 로직을 공통화하거나 표현하기 복잡한 로직을 캡슐화 할 수 있어서
	 * JSP의 구현 코드를 간결하게 만들 수 있습니다.
	 * 
	 * 그리고, 커스텀 태그를 모아 놓은 것을 커스텀 태그 라이브러리라고 합니다.
	 * 
	 * # 대표적인 태그 라이브러리
	 * - JSTL(JavaServer Pages Standard Tag Library)
	 * - spring-form JSP Tag Library
	 * - spring JSP Tag Library
	 * - Spring Security JSP Tag Library
	 */
	
	/*
	 * 4. 표현언어(EL)
	 * JSP는 EL(Expression Language)이라는 표현 언어를 사용해 값의 참조,출력,연산을 할 수 있습니다.
	 * EL식은 ${...} 또는 #{...} 형식으로 작성합니다.
	 */
	
	// 1) 자바빈즈 프로퍼티를 조회하는 경우 '속성명.프로퍼티명'을 지정한다.
	@RequestMapping(value="/home0101", method = RequestMethod.GET)
	public String home0101(Model model) {
		Member member = new Member();
		member.setUserId("hongkd");
		member.setPassword("1234");
		member.setEmail("aaa@ccc.com");
		member.setUserName("홍길동");
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2022);
		cal.set(Calendar.MONTH, 10);
		cal.set(Calendar.DAY_OF_MONTH, 7);
		member.setDateOfBirth(cal.getTime());
		model.addAttribute(member);
		return "home/home0101";
	}
	
	// 1-2) 
	@RequestMapping(value="/home0102", method = RequestMethod.GET)
	public String home0102(Model model) {
		Member member = new Member();
		
		String[] hobbyArray = {"Music","Movie"};
		member.setHobbyArray(hobbyArray);
		
		List<String> hobbyList = new ArrayList<String>();
		hobbyList.add("Music");
		hobbyList.add("Movie");
		member.setHobbyList(hobbyList);
		model.addAttribute(member);
		return "home/home0102";
	}
	
	// 1-3)
	@RequestMapping(value="/home0103", method = RequestMethod.GET)
	public String home0103(Model model) {
		Member member = new Member();
		
		Address address = new Address();
		address.setPostCode("080908");
		address.setLocation("Daejeon");
		member.setAddress(address);
		
		model.addAttribute(member);
		return "home/home0103";
	}
	
	// 1-4)
	@RequestMapping(value="/home0104", method = RequestMethod.GET)
	public String home0104(Model model) {
		Member member = new Member();
		
		List<Card> cardList = new ArrayList<Card>();
		
		Card card1 = new Card();
		card1.setNo("12345");
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2022);
		cal.set(Calendar.MONTH, 10);
		cal.set(Calendar.DAY_OF_MONTH, 7);
		card1.setValidMonth(cal.getTime());
		cardList.add(card1);
		
		Card card2 = new Card();
		card2.setNo("67890");
		
		cal.set(Calendar.YEAR, 2022);
		cal.set(Calendar.MONTH, 11);
		cal.set(Calendar.DAY_OF_MONTH, 17);
		card2.setValidMonth(cal.getTime());
		cardList.add(card2);
		
		member.setCardList(cardList);
		model.addAttribute(member);
		return "home/home0104";
	}
	
	// 1-5)
	@RequestMapping(value="/home0105", method = RequestMethod.GET)
	public String home0105(Model model) {
		Map memberMap = new HashMap();
		memberMap.put("userId", "hongkd");
		memberMap.put("password", "1234");
		memberMap.put("email", "aaa@ccc.com");
		memberMap.put("userName", "홍길동");
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2022);
		cal.set(Calendar.MONTH, 10);
		cal.set(Calendar.DAY_OF_MONTH, 11);
		
		memberMap.put("dateOfBirth", cal.getTime());
		
		model.addAttribute("memberMap", memberMap);
		return "home/home0105";
	}
	
	// 2) 산술 연산자 사용
	@RequestMapping(value="/home0201", method = RequestMethod.GET)
	public String home0201(Model model) {
		int coin = 1000;
		model.addAttribute("coin", coin);
		return "home/home0201";
	}
	
	// 3) 비교 연산자 사용
	@RequestMapping(value="/home0202", method = RequestMethod.GET)
	public String home0202(Model model) {
		int coin = 1000;
		model.addAttribute("coin", coin);
		return "home/home0202";
	}
	
	// 3-2) 비교 연산자 사용
	@RequestMapping(value="/home0203", method = RequestMethod.GET)
	public String home0203(Model model) {
		String userId = "hongkd";
		model.addAttribute("userId", userId);
		return "home/home0203";
	}
	
	// 4) empty 연산자 사용
	@RequestMapping(value="/home0301", method = RequestMethod.GET)
	public String home0301(Model model) {
//		Member member = new Member();
//		member.setUserId("hongkd");
//		model.addAttribute(member);
		return "home/home0301";
	}
	
	// 4-2) empty 연산자 사용
	@RequestMapping(value="/home0302", method = RequestMethod.GET)
	public String home0302(Model model) {
		Member member = new Member();
		model.addAttribute(member);
		return "home/home0302";
	}
	
	// 4-3) empty 연산자 사용
	@RequestMapping(value="/home0303", method = RequestMethod.GET)
	public String home0303(Model model) {
		Member member = new Member();
		member.setUserId("");
		model.addAttribute(member);
		return "home/home0303";
	}
	
	// 4-4)
	@RequestMapping(value="/home0304", method = RequestMethod.GET)
	public String home0304(Model model) {
		Member member = new Member();
		member.setUserId("hongkd");
		model.addAttribute(member);
		return "home/home0304";
	}
	
	// 4-5)
	@RequestMapping(value="/home0305", method = RequestMethod.GET)
	public String home0305(Model model) {
		Member member = new Member();
		String[] hobbyArray = null;
		member.setHobbyArray(hobbyArray);
		model.addAttribute(member);
		return "home/home0305";
	}
	
	// 4-6) 
	@RequestMapping(value="/home0306", method = RequestMethod.GET)
	public String home0306(Model model) {
		Member member = new Member();
		String[] hobbyArray = {};
		member.setHobbyArray(hobbyArray);
		model.addAttribute(member);
		return "home/home0306";
	}
	
	// 4-7)
	@RequestMapping(value="/home0307", method = RequestMethod.GET)
	public String home0307(Model model) {
		Member member = new Member();
		String[] hobbyArray = {"Music","Movie"};
		member.setHobbyArray(hobbyArray);
		model.addAttribute(member);
		return "home/home0307";
	}
	
	// 4-8)
	@RequestMapping(value="/home0308", method = RequestMethod.GET)
	public String home0308(Model model) {
		Member member = new Member();
		model.addAttribute(member);
		return "home/home0308";
	}
	
	// 4-9)
	@RequestMapping(value="/home0309", method = RequestMethod.GET)
	public String home0309(Model model) {
		Member member = new Member();
		List<String> hobbyList = new ArrayList<String>();
		member.setHobbyList(hobbyList);
		model.addAttribute(member);
		return "home/home0309";
	}
	
	@RequestMapping(value="/home0311", method = RequestMethod.GET)
	public String home0311(Model model) {
		Map memberMap = new HashMap();
		model.addAttribute(memberMap);
		return "home/home0311";
	}
}


















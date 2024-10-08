package org.joonzis.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.joonzis.dto.StudentDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	// @RequestMapping : URL-Mapping
	// 메소드를 대상으로 어노테이션을 붙인다.
	// value="/" : 컨텍스트 패스를 의미, 서버:포트/디폴트패키지
	// method=RequestMethod.GET : get/post 방식
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "member/input";
	}
	@RequestMapping(value = "result", method = RequestMethod.POST)
	public String login(@RequestParam("id") String id, @RequestParam("pw") String pw, Model model) {
		model.addAttribute("id", id);
		model.addAttribute("pw", pw);
		return "member/output";
	}
	
	@RequestMapping(value = "a/b/c/d/e")
	// method 생략 가능 기본 값 = get 방식
	// value 생략하여 ("a/b/c/d/e")로 쓸 수 있다.
	public String goView01() {
		// 1. 리턴 타입 : 뷰(view)를 리턴하기 때문에 언제나 String
		// 2. 메소드명 : goView01은 아무런 의미가 없다. (메소드끼리 이름만 다르면 된다.)
		// 3. 리턴 : "/view01", "view01" 차이 없다
		return "view01";
	}
	
	// "admin/view02" url로 요청
	// goView02 메소드 생성
	// "id" = "admin", "pw" ="1234"를 저장하여 화면에서 출력
	// admin/view02로 이동
	@RequestMapping("admin/view02")
	public String goView02(Model model) {
		model.addAttribute("id", "admin");
		model.addAttribute("pw", "1234");
		return "admin/view02";
	}
	// URLMapping과 도착하는 jsp의 경로가 같다면 
	// 메소드의 리턴타입을 void로 처리해도 알아서 jsp를 찾아간다.
	@RequestMapping("index")
	public void goindex() {}
	
	@RequestMapping(value ="v01", method = RequestMethod.POST)
	public String goResult1(StudentDTO sDto, Model model) {
		model.addAttribute("sDto", sDto);
		return "result";
	}
	
	@RequestMapping(value ="v02", method = RequestMethod.POST)
	public String goResult2(@ModelAttribute("s") StudentDTO dto) {
		return "result";
	}
	
}

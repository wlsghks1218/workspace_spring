package org.joonzis.controller;

import org.joonzis.domain.TestVO;
import org.joonzis.domain.Ticket;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import lombok.extern.log4j.Log4j;

@Log4j
@RequestMapping("/test")
//RESTful 웹 서비스를 구현하는 컨트롤러 클래스를 정의합니다.
//이 클래스는 Spring의 REST 컨트롤러로, HTTP 요청에 대한 응답을 처리합니다.
@RestController
public class TestController {
	// produces는 응답 데이터의 타입
	@GetMapping(value = "/getText", produces = "text/plain; charset=utf-8")
	public String getText() {
		// 로그를 기록합니다.
		// MediaType.TEXT_PLAIN_VALUE는 "text/plain"을 의미하며, 이 MIME 타입을 로그로 기록합니다.
		log.info("MimeType : " + MediaType.TEXT_PLAIN_VALUE);
		return "안녕하세요";
	}
	// 타입을 지정해주지 않으면 xml 파일로 리턴됨
	// json으로 응답 설정하면 url 뒤에 .json을 붙이면 된다. <gson 라이브러리 역할>
	@GetMapping(value = "/getObject", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public TestVO getObject() {
		return new TestVO(100, "kim");
	}
	/*
	 * 요청 URL : /test/check
	 * 파라미터 : 실수형 age
	 * 리턴타입 : TestVO
	 * - vo 객체를 생성
	 * - no 필드는 0으로 고정
	 * - 전달 받은 age를 문자열로 name 필드에 저장 
	 * - json 형태로 TestVO 반환
	 */
	@GetMapping(value = "/check", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE })
	// "/check" 경로로 GET 요청이 오면 이 메소드가 호출됨. 응답 형식은 JSON 또는 XML.
	public ResponseEntity<TestVO> check(double age) {
		// 메소드의 파라미터로 전달된 double 타입의 age 값을 사용.
		TestVO vo = new TestVO(0, "" + age);
		// TestVO 객체를 생성하고, age 값을 String으로 변환하여 두 번째 파라미터로 전달.
		ResponseEntity<TestVO> result = null;
		// ResponseEntity 객체를 생성할 변수 선언.
		if (age > 150) {
			// age가 150보다 크면,
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(vo);
			// HTTP 상태 코드를 502 (BAD_GATEWAY)로 설정하고, TestVO 객체를 응답 본문으로 설정.
		} else {
			// age가 150 이하이면,
			result = ResponseEntity.status(HttpStatus.OK).body(vo);
			// HTTP 상태 코드를 200 (OK)으로 설정하고, TestVO 객체를 응답 본문으로 설정.
		}
		return result;
		// 결과로 설정된 ResponseEntity 객체를 반환.
	}
	@GetMapping("/product/{cat}/{pid}")
	public String[] getPath(@PathVariable("cat") String cat, @PathVariable("pid") int pid) {
		return new String[] {"category : " + cat + ", productId : " + pid};
	}
	// produces 속성을 부여하지 않는 경우 기본 리턴 값은 xml
	// 하지만 .json을 url에 입력하면 json으로 변경되는 이유는 Jackson-databind / Jackson-dataformat-xml 라이브러리 덕분
	@PostMapping("/ticket")
	public Ticket convert(@RequestBody Ticket t) {
		log.info("convert ticket : " + t);
		
		// java객체를 json객체로 변환하는 방법
		String result = new Gson().toJson(t);
		log.info(result);
		return t;
	}
	// @RequestBody => json을 알아서 java 객체로 변환해줌
	// java에서 json객체 만드는 법
	@GetMapping("/info")
	public String makeJson() {
		JsonObject json = new JsonObject();
		json.addProperty("name", "kim");
		json.addProperty("age", 10);
		json.addProperty("job", "student");
		JsonArray ja = new JsonArray();
		for(int i=0; i<5; i++) {
			JsonObject j = new JsonObject();
			j.addProperty("user"+i, i);
			ja.add(j);;
		}
		json.add("users", ja);
		return json.toString();
	}
}

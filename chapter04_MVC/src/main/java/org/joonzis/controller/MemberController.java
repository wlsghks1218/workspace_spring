package org.joonzis.controller;

import java.util.ArrayList;
import java.util.List;

import org.joonzis.domain.AuthVO;
import org.joonzis.domain.MemberVO;
import org.joonzis.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/member/*")
public class MemberController {

	@Autowired
	private MemberService service;
	
    @Autowired
    private PasswordEncoder pwencoder;
	
	@PostMapping("/signIn")
	public String signIn(MemberVO vo) {
		vo.setUserPw(pwencoder.encode(vo.getUserPw()));
		int result = service.signIn(vo);
		if(result != 1) {
			log.info("입력 실패");
		}else {
			log.info("입력 성공");
		}
		return "/customLogin";
	}
}

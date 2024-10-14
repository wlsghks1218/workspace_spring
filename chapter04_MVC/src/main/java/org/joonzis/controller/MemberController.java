package org.joonzis.controller;

import java.util.ArrayList;
import java.util.List;

import org.joonzis.domain.AuthVO;
import org.joonzis.domain.MemberVO;
import org.joonzis.domain.ReplyVO;
import org.joonzis.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
    // 신규 댓글 추가하기
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

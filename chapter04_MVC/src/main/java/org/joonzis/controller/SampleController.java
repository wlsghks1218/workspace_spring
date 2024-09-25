package org.joonzis.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/sample")
public class SampleController {
	
	@GetMapping("/all")
	public String doAll() {
		log.info("do all can access everybody");
		return "sample/all";
	}
	
	@GetMapping("/member")
	public String doMember() {
		log.info("logined member");
		return "sample/member";
	}
	
	@GetMapping("/admin")
	public String doAdmin() {
		log.info("admin only");
		return "sample/admin";
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MEMBER')")
	@GetMapping("/annoMember")
	public String doAnnoMember() {
		log.error("logined annoMember");
		return "sample/annoMember";
	}
	
	// Secured는 값만 추가할 수 있으므로, 여러 개를 사용할 때에는 배열로 표현
	@Secured({"ROLE_ADMIN"})
	@GetMapping("/annoAdmin")
	public String doAnnoAdmin() {
		log.warn("logined annoAdmin");
		return "sample/annoAdmin";
	}
}

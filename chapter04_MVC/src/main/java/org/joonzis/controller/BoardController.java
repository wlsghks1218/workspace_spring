package org.joonzis.controller;

import org.joonzis.domain.BoardVO;
import org.joonzis.domain.Criteria;
import org.joonzis.domain.PageDTO;
import org.joonzis.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/board/*")
public class BoardController {
	/*
	 * Task		- URL				- Method	- Parameter
	 * 전체 목록	- /board/list 		- get
	 * 등록		- /board/register	- post		- 모든 항목
	 * 조회		- /board/read		- get		- bno
	 * 삭제		- /board/remove		- post		- bno
	 * 수정		- /board/modify		- post		- 모든 항목
	 */
	@Autowired
	private BoardService service;
	
	// 전체 게시글 조회
	@GetMapping("/list")
	public String list(@RequestParam(value = "pageNum", defaultValue="1") int pageNum, @RequestParam(value="amount", defaultValue="5") int amount, Model model) {

		Criteria cri = new Criteria(pageNum, amount);
        int total = service.getTotalRecordCount();
        PageDTO pdto = new PageDTO(cri, total);
        model.addAttribute("pageMaker", pdto);
		log.info("---- list...");
		
		model.addAttribute("list", service.getListWithPaging(cri));
		model.addAttribute("total", total);
		return "/board/list";
	}
	
	 // 전체 게시글 조회
//	 @GetMapping("/list")
//	 public String list(Model model, Criteria cri){
//	    int total = service.getTotalRecordCount();
//	    PageDTO pdto = new PageDTO(cri, total);
//		log.info("list...");
//		if(cri.getPageNum()==0 || cri.getAmount() ==0){
//			cri.setPageNum(1);
//			cri.setAmount(5);
//		}
//	    model.addAttribute("pageMaker", pdto);
//	    model.addAttribute("total", total);
//		model.addAttribute("list", service.getListWithPaging(cri));
//		return "/board/list";
//	 }
		
	
	// 게시글 등록
	@PostMapping("/register")
	public String register(BoardVO vo, RedirectAttributes rttr) {
		log.info("----- register..." + vo);
		service.register(vo);
		rttr.addFlashAttribute("result", "success");
		return "redirect:/board/list";
		// redirect: 로 리다이렉트 태울 수 있음
	}
	
	@GetMapping("register")
	public String register() {
		log.info("register...");
		return "/board/register";
	}
	
	// 게시글 조회
	@GetMapping("/get")
	public String get(@RequestParam("bno") int bno, Model model) {
		log.info("----- get..." + bno);
		model.addAttribute("vo", service.get(bno));
		return "/board/get";
	}
//	@GetMapping({"/get", "/modify"})
//	아래 get방식 modify를 배열 처리 후 method 방식을 void로 변경 (동일한 코드의 메소드는 배열 처리해서 합칠 수 있음)
//	public void get(@RequestParam("bno") int bno, Model model) {
//		log.info("----- get..." + bno);
//		model.addAttribute("vo", service.get(bno));
//	}	
	
	
//	// 게시글 수정
//	@GetMapping("/modify")
//	public String modify(@RequestParam("bno") int bno, Model model) {
//		log.info("----- modify...");
//		model.addAttribute("vo", service.get(bno));
//		return "/board/modifyPage";
//	}
	
	// 게시글 수정
	@PostMapping("/modify")
	public String modify(BoardVO vo, Model model) {
		log.info("----- modify..." + vo);
		service.modify(vo);
		model.addAttribute("vo", service.get(vo.getBno()));
		return "/board/get";
//		return "redirect:/board/get?bno="+vo.getBno();
	}
	
	// 게시글 삭제
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") int bno) {
		log.info("----- remove..." + bno);
		service.remove(bno);
		return "redirect:/board/list";
	}
}

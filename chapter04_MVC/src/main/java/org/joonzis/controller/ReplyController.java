package org.joonzis.controller;

import java.util.List;

import org.joonzis.domain.ReplyVO;
import org.joonzis.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j;

@Log4j
@RestController
@RequestMapping("/reply/")
public class ReplyController {
	
	/*
	 * 동작에 따른 url 방법(http 전송 방식)
	 * 1. 등록 - /reply/new - POST
	 * 2. 전체 댓글 - /reply/pages/:bno - GET
	 * 3. 조회 - /reply/:rno - GET
	 * 4. 삭제 - /reply/:rno - DELETE
	 * 5. 수정 - /reply/:rno - PUT or PATCH
	 * 
	 * 
	 */
	
	@Autowired
	private ReplyService service;
	
	// 댓글 추가
	// consumes : 받는 파라미터 타입
	// produces : 응답하는 데이터의 타입
	@PostMapping(value = "/new", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> replyInsert(@RequestBody ReplyVO vo){
		log.info("--Controller.replyInsert--"+vo);
		int resultInsert = service.replyInsert(vo);
		return resultInsert == 1 ?
				new ResponseEntity<String>("success", HttpStatus.OK) : 
				new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// 모든 댓글 불러오기
	@GetMapping(value = "/pages/{bno}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<ReplyVO>> getReplyList(@PathVariable("bno") int bno){
		log.info("--Controller.getReplyList");
		return new ResponseEntity<>(service.getReplyList(bno), HttpStatus.OK);
	}
	
	// 댓글 한개 불러오기
	@GetMapping(value = "/{rno}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<ReplyVO> replyRead(@PathVariable("rno") int rno){
		log.info("--Controller.replyRead--");
		return new ResponseEntity<>(service.replyRead(rno), HttpStatus.OK);
	}
	
	// 댓글 삭제
	@DeleteMapping(value = "/{rno}", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> replyDelete(@PathVariable("rno") int rno){
		log.info("--Controller.replyDelete--");
		int resultDelete = service.replyDelete(rno);
		return resultDelete == 1 ?
				new ResponseEntity<String>("success", HttpStatus.OK) : 
				new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// 댓글 수정
//	@RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH})
//	@PatchMapping(value="/{rno}")
	@PutMapping(value="/{rno}", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> replyUpdate(@RequestBody ReplyVO vo, @PathVariable("rno") int rno){
		log.info("--Controller.replyUpdate--rno:" + rno + "vo : "+ vo);
		int resultUpdate = service.replyUpdate(vo);
		log.info("resultUpdate : " + resultUpdate);
		return resultUpdate == 1 ?
				new ResponseEntity<String>("success", HttpStatus.OK) : 
				new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
}

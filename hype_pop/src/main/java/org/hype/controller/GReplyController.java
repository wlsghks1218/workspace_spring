package org.hype.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hype.domain.GReplyVO;
import org.hype.service.GReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j;

@Log4j
@RestController
@RequestMapping("/gReply/")
public class GReplyController {
    
    @Autowired
    private GReplyService gService;
    
    @PostMapping(value = "/new", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> replyInsert(@RequestBody GReplyVO vo) {
        // 로그에 요청 내용을 출력
        log.info("--Controller.insertGReply--" + vo);
        log.info("asdf" + vo.getGNo());
        log.info("asdfasdf" + vo.getGScore());
        log.info("asdfasdfasdfasdf" + vo.getUserNo());
        log.info("후기 내용: " + vo.getGComment());

        int resultInsert = gService.insertGReply(vo);
		return resultInsert == 1 ?
				new ResponseEntity<String>("success", HttpStatus.OK) : 
				new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // 비로그인 상태에서 모든 댓글 목록 불러오기
//    @GetMapping(value = "/goodsDetails/{gNo}", produces = MediaType.TEXT_PLAIN_VALUE)
//    public 
    
    
	// 로그인 상태에서 내 댓글, 모든 댓글(내 댓글 제외) 불러오기
	@GetMapping(value = "/goodsDetails/{gNo}/{userNo}", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<Map<String, Object>> getReplyList(@PathVariable("gNo") int gNo, @PathVariable("userNo") int userNo){
    	Map<String, Object> response = new HashMap<>();
		log.info("--Controller.getReplyList gNo:" + gNo + "userNo: {}" + userNo);
		Object result1 = gService.getAllReplyList(gNo, userNo);
		Object result2 = gService.getMyReply(gNo, userNo);
		response.put("service1Result", result1);
		response.put("service2Result", result2);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
//	// 댓글 삭제
//	@DeleteMapping(value = "/{rno}", produces = MediaType.TEXT_PLAIN_VALUE)
//	public ResponseEntity<String> replyDelete(@PathVariable("rno") int rno){
//		log.info("--Controller.replyDelete--");
//		int resultDelete = service.replyDelete(rno);
//		return resultDelete == 1 ?
//				new ResponseEntity<String>("success", HttpStatus.OK) : 
//				new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
//	}
//	
//	// 댓글 수정
////	@RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH})
////	@PatchMapping(value="/{rno}")
//	@PutMapping(value="/{rno}", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
//	public ResponseEntity<String> replyUpdate(@RequestBody ReplyVO vo, @PathVariable("rno") int rno){
//		log.info("--Controller.replyUpdate--rno:" + rno + "vo : "+ vo);
//		int resultUpdate = service.replyUpdate(vo);
//		log.info("resultUpdate : " + resultUpdate);
//		return resultUpdate == 1 ?
//				new ResponseEntity<String>("success", HttpStatus.OK) : 
//				new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
//	}
}

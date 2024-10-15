package org.hype.controller;

import java.util.HashMap;
import java.util.Map;

import org.hype.domain.GReplyVO;
import org.hype.service.GReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
        log.info("gNo: " + vo.getGno());
        log.info("userNo: " + vo.getUserNo());
        log.info("gComment: " + vo.getGcomment());
        log.info("gScore: " + vo.getGscore());
        int resultInsert = gService.insertGReply(vo);
		return resultInsert == 1 ?
				new ResponseEntity<String>("success", HttpStatus.OK) : 
				new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // 비로그인 상태에서 모든 댓글 목록 불러오기
//    @GetMapping(value = "/goodsDetails/{gNo}", produces = MediaType.TEXT_PLAIN_VALUE)
//    public 
    
    
	// 로그인 상태에서 내 댓글, 모든 댓글(내 댓글 제외) 불러오기
    @GetMapping(value = "/{gno}/{userNo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> getReplyList(@PathVariable("gno") int gno, @PathVariable("userNo") int userNo){
    	Map<String, Object> response = new HashMap<>();
		log.info("--Controller.getReplyList gno:" + gno + "userNo:" + userNo);
		Object result1 = gService.getAllReplyList(gno, userNo);
		Object result2 = gService.getMyReply(gno, userNo);
		response.put("service1Result", result1);
		response.put("service2Result", result2);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
    
    @GetMapping(value = "/avgStars", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> goodsAvgStars() {
        // gService.getAvgStars()는 평균 별점을 반환하는 메서드로 가정
        double avgStars = gService.getAvgStars();  // 평균 별점 반환 메서드
        String avgStarsString = String.valueOf(avgStars);  // double 값을 String으로 변환
        
        return new ResponseEntity<>(avgStarsString, HttpStatus.OK);  // 문자열로 반환
    }
    
    // 댓글을 달았는지 확인
    @GetMapping(value = "/chkReplied", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<Integer> chkReplied(@PathVariable("userNo") int userNo){
    	int result = gService.chkReplied(userNo);
    	return new ResponseEntity<>(result, HttpStatus.OK);
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

package org.hype.controller;

import org.hype.domain.GReplyVO;
import org.hype.service.GReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j;

@Log4j
@RestController
@RequestMapping("/gReply/")
public class gReplyController {
    
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

}

package org.hype.controller;

import org.hype.domain.gReplyVO;
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

@RestController
@Log4j
@RequestMapping("/gReply/")
public class GReplyController {
    
    @Autowired
    private GReplyService gService;

    @PostMapping(value = "/new", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> replyInsert(@RequestBody gReplyVO vo) {
        // 로그에 요청 내용을 출력
        log.info("--Controller.insertGReply--" + vo);
        log.info("asdf" + vo.getGNo());
        log.info("asdfasdf" + vo.getGScore());
        log.info("asdfasdfasdfasdf" + vo.getUserNo());
        log.info("후기 내용: " + vo.getGComment());

        // 리뷰 삽입 시도
        int resultInsert = gService.insertGReply(vo);
        
        // 삽입 결과에 따라 응답 반환
        if (resultInsert == 1) {
            return new ResponseEntity<>("success", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

//package org.hype.controller;
//
//import java.util.List;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import lombok.extern.log4j.Log4j;
//
//@Log4j
//@RestController
//@RequestMapping("/reply")
//public class ReplyController {
//
//    @Autowired
//    private ReplyService service;
//
//    // 1. ��� ���
//    @PostMapping(value = "/new", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
//    public ResponseEntity<String> create(@RequestBody ReplyVO rvo) {
//        log.info("replyVO : " + rvo);
//        
//        int insertCount = service.register(rvo);
//        
//        log.info("insertCount : " + insertCount);
//        return insertCount == 1 ? 
//            new ResponseEntity<>("success", HttpStatus.OK) :
//            new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    // 2. ��� ��� ��ȸ
//    @GetMapping(value = "pages/{psNo}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
//    public ResponseEntity<List<ReplyVO>> getList(@PathVariable("bno") int bno) {
//        log.info("getList...." + bno);
//        List<ReplyVO> replies = service.getList(bno);
//        return new ResponseEntity<>(replies, HttpStatus.OK);
//    }
//      




//    �����ؼ� �����Ӱ� ��� �ҷ����⿡ ���ø� �˴ϴ�! ���� ������ �ʿ��Ұſ���






//    // 3. �� ��� ��ȸ
//    @GetMapping(value = "/{userNo}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
//    public ResponseEntity<ReplyVO> get(@PathVariable("userNo") int userNo) {
//        log.info("get......." + userNo);
//        ReplyVO reply = service.get(userNo);
//        return new ResponseEntity<>(reply, HttpStatus.OK);
//    }
//
//    // 4. ��� ����
//    @DeleteMapping(value = "/{userNo}", produces = MediaType.TEXT_PLAIN_VALUE)
//    public ResponseEntity<String> remove(@PathVariable("userNo") int userNo) {
//        log.info("remove......." + psNo);
//        return service.remove(psNo) == 1 ?
//            new ResponseEntity<>("success", HttpStatus.OK) :
//            new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    // 5. ��� ����
//    @RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH}, value = "/{userNo}", produces = MediaType.TEXT_PLAIN_VALUE, consumes = "application/json")
//    public ResponseEntity<String> modify(@PathVariable("userNo") int userNo, @RequestBody psReplyVO rvo) {
//        log.info("rvo : " + rvo);
//        log.info("rno : " + rno);
//        
//        int modifyCount = service.modify(rvo);
//        
//        log.info("modifyCount : " + modifyCount);
//        
//        return modifyCount == 1 ? 
//            new ResponseEntity<>("success", HttpStatus.OK) :
//            new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//}

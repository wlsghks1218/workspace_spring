package org.joonzis.mapper;

import java.util.List;

import org.joonzis.domain.ReplyVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class ReplyMapperTests {
	
	@Autowired
	private ReplyMapper mapper;
	
//	@Test
//	public void testInsert() {
//		log.info("------testInsert");
//		ReplyVO vo = new ReplyVO();
//		vo.setBno(1);
//		vo.setReply("댓글 테스트");
//		vo.setReplyer("테스트 댓글러");
//		
//		int result = mapper.replyInsert(vo);
//		log.info(result);
//	}
	
//	@Test
//	public void testRead() {
//		log.info("---testRead");
//		List<ReplyVO> list = mapper.getReplyList(1);
//		for(ReplyVO vo : list) {
//			log.info(vo);
//		}
//	}
	
//	@Test
//	public void testDelete() {
//		log.info("---testDelete");
//		int result = mapper.replyDelete(1);
//		log.info(result);
//	}
	
//	@Test
//	public void testRead() {
//		log.info("---testRead");
//		ReplyVO vo = mapper.replyRead(2);
//		log.info(vo);
//	}
	
//	@Test
//	public void testUpdate() {
//		log.info("---testUpdate");
//		ReplyVO vo = new ReplyVO();
//		vo.setReply("수정된댓글");
//		vo.setRno(2);
//		int result = mapper.replyUpdate(vo);
//		log.info(result);
//	}
	
}

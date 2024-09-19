package org.joonzis.service;

import java.util.List;

import org.joonzis.domain.BoardVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class BoardServiceTests {
	
	@Autowired
	private BoardService service;
	
//	@Test
//	public void testGetList() {
//		List<BoardVO> list = service.getList();
//		for(BoardVO vo : list) {
//			log.info(vo);
//		}
////		 한줄로 줄일 수 있음
////		service.getList().forEach(vo -> log.info(vo));
//	}
	
//	@Test
//	public void testRegister() {
//		BoardVO bVo = new BoardVO();
//		bVo.setTitle("테스트제목9");
//		bVo.setContent("테스트내용9");
//		bVo.setWriter("User09");
//		int result = service.register(bVo);
//		log.info(result);
//	}
	
	
//	@Test
//	public void testDelete() {
//		int result = service.remove(21);
//		log.info(result);
//	}
	
//	@Test
//	public void testUpdate() {
//		BoardVO bVo = new BoardVO();
//		bVo.setBno(21);
//		bVo.setTitle("테스트제목21");
//		bVo.setContent("테스트내용21");
//		bVo.setWriter("User021");
//		int result = service.modify(bVo);
//		log.info(result);
//	}
	
//	@Test
//	public void testRead() {
//		BoardVO bVo = service.get(21);
//		log.info(bVo);
//	}
}

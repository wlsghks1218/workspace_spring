package org.joonzis.mapper;

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
public class BoardMapperTests {
	@Autowired
	private BoardMapper mapper;
	
//	@Test
//	public void testGetList() {
//		List<BoardVO> list = mapper.getList();
//		for(BoardVO vo : list) {
//			log.info(vo);
//		}
//	}
	
//	@Test
//	public void testInsert() {
//		BoardVO bVo = new BoardVO();
//		bVo.setTitle("테스트제목8");
//		bVo.setContent("테스트내용8");
//		bVo.setWriter("User08");
//		int result = mapper.insert(bVo);
//		log.info(result);
//	}
	
//	@Test
//	public void testRead() {
//		BoardVO bVo = mapper.read(1);
//		log.info(bVo);
//	}
	
//	@Test
//	public void testDelete() {
//		int result = mapper.delete(8);
//		log.info(result);
//	}
	
//	@Test
//	public void testUpdate() {
//		BoardVO bVo = new BoardVO();
//		bVo.setBno(4);
//		bVo.setTitle("테스트제목44");
//		bVo.setContent("테스트내용44");
//		bVo.setWriter("User044");
//		int result = mapper.update(bVo);
//		log.info(result);
//	}
}

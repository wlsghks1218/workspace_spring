package org.joonzis.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hype.domain.GReplyVO;
import org.hype.domain.gCatVO;
import org.hype.domain.goodsVO;
import org.hype.service.GReplyService;
import org.hype.service.GoodsService;
import org.junit.Test; 
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;


@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class SampleServiceTest {
	@Autowired
	private GoodsService gService;
	@Autowired
	private GReplyService gRService;
	
	@Test
	public void test() throws Exception{
		int gno = 29;
		int userNo = 2;
		int offset = 0;
		int size = 5;
		List<GReplyVO> replyList = gRService.getAllReplyListWithPaging(gno, userNo, offset, size);
		
		for(GReplyVO vo : replyList) {
			log.info(vo.getGreplyNo());
		}
	}
}
package org.joonzis.service;

import java.util.List;

import org.hype.domain.gReplyVO;
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
		System.out.println("gRService: " + gRService); // null인지 확인
		gReplyVO gVo = new gReplyVO();
		gVo.setGComment("hi");
		gVo.setGNo(10);
		gVo.setGScore(4);
		gVo.setUserNo(1);
		int result = gRService.insertGReply(gVo);
		System.out.println(result);
	}
}
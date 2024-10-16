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
        Map<String, Object> result1 = gService.getListByInterestOneNotLogin();
        String category1 = (String) result1.get("category");
        List<goodsVO> interestOneNotLogin = (List<goodsVO>) result1.get("goodsList");
        
        Map<String, Object> result2 = gService.getListByInterestTwoNotLogin();
        String category2 = (String) result2.get("category");
        List<goodsVO> interestTwoNotLogin = (List<goodsVO>) result2.get("goodsList");
        
        Map<String, Object> result3 = gService.getListByInterestThreeNotLogin();
        String category3 = (String) result3.get("category");
        List<goodsVO> interestThreeNotLogin = (List<goodsVO>) result3.get("goodsList");
        
        log.info("cat : " + category1);
        log.info("cat : " + category2);
        log.info("cat : " + category3);
        log.info("관심사1 vo " + interestOneNotLogin);
        log.info("관심사2 vo " + interestTwoNotLogin);
        log.info("관심사3 vo " + interestThreeNotLogin);
	}
}
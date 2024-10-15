package org.joonzis.service;

import java.util.ArrayList;
import java.util.List;

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
    	gCatVO vo = gService.getCategory(10);
        List<String> categoryList = new ArrayList<>();
        if (vo.getHealthBeauty() == 1) {
            categoryList.add("healthBeauty");
        }
        if (vo.getGame() == 1) {
            categoryList.add("game");
        }
        if (vo.getCulture() == 1) {
            categoryList.add("culture");
        }
        if (vo.getShopping() == 1) {
            categoryList.add("shopping");
        }
        if (vo.getSupply() == 1) {
            categoryList.add("supply");
        }
        if (vo.getKids() == 1) {
            categoryList.add("kids");
        }
        if (vo.getDesign() == 1) {
            categoryList.add("design");
        }
        if (vo.getFoods() == 1) {
            categoryList.add("foods");
        }
        if (vo.getInterior() == 1) {
            categoryList.add("interior");
        }
        if (vo.getPolicy() == 1) {
            categoryList.add("policy");
        }
        if (vo.getCharacter() == 1) {
            categoryList.add("character");
        }
        if (vo.getExperience() == 1) {
            categoryList.add("experience");
        }
        if (vo.getCollaboration() == 1) {
            categoryList.add("collaboration");
        }
        if (vo.getEntertainment() == 1) {
            categoryList.add("entertainment");
        }
        categoryList.forEach(category -> {
            System.out.println("Selected category: " + category);
        });
	}
}
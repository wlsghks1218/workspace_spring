package org.hype.controller;

import java.util.List;

import org.hype.domain.goodsVO;
import org.hype.domain.rankVO;
import org.hype.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/goodsStore/*")
public class GoodsController {
	
	@Autowired
	private GoodsService gService;
	
    @GetMapping("/goodsDetails")
    public String goodsSearch(@RequestParam("gNo") int gNo, Model model) {
        System.out.println("굿즈 상세 페이지 gNo : " + gNo);
        model.addAttribute("goods", gService.getOneByGno(gNo));
        log.info("like count 는 " + gService.getOneByGno(gNo).getLikeCount());
        return "/goodsStore/goodsDetails";
    }
 
    @GetMapping("/goodsMain")
    public String goodsMain(Model model) {
        // 메인 페이지를 보여줍니다
        log.info("메인 페이지로 이동");
        List<goodsVO> vo1 = gService.getListByLikeCount();
        vo1.forEach(item -> log.info("vo1는 " + item.getGName()));
        model.addAttribute("likeGoods", gService.getListByLikeCount());
        
        List<goodsVO> vo2 = gService.getListByInterestOneNotLogin();
        vo2.forEach(item -> log.info("vo2는 " + item.getGName()));
        
        List<goodsVO> vo3 = gService.getListByInterestTwoNotLogin();
        vo3.forEach(item -> log.info("vo3는 " + item.getGName()));
        
        List<goodsVO> vo4 = gService.getListByInterestThreeNotLogin();
        vo4.forEach(item -> log.info("vo4는 " + item.getGName()));
        
        model.addAttribute("interestOneNotLogin", gService.getListByInterestOneNotLogin());
        model.addAttribute("interestTwoNotLogin", gService.getListByInterestTwoNotLogin());
        model.addAttribute("interestThreeNotLogin", gService.getListByInterestThreeNotLogin());
        return "/goodsStore/goodsMain"; // 메인 페이지 JSP의 경로
    }

    @GetMapping("/goodsSearch")
    public String goodsSearch() {
        // 검색 페이지로 이동합니다
        log.info("상품 검색 페이지로 이동");
        
        return "/goodsStore/goodsSearch"; // 검색 페이지 JSP의 경로
    }
}
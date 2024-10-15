package org.hype.controller;

import java.util.List;

import org.hype.domain.SearchRequest;
import org.hype.domain.gCatVO;
import org.hype.domain.goodsVO;
import org.hype.domain.rankVO;
import org.hype.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/goodsStore/*")
public class GoodsController {
	
	@Autowired
	private GoodsService gService;
	
    @GetMapping("/goodsDetails")
    public String goodsSearch(@RequestParam("gno") int gno, Model model) {
        System.out.println("굿즈 상세 페이지 gno : " + gno);
        model.addAttribute("goods", gService.getOneByGno(gno));
        log.info("like count 는 " + gService.getOneByGno(gno).getLikeCount());
        return "/goodsStore/goodsDetails";
    }
 
    @GetMapping("/goodsMain")
    public String goodsMain(Model model) {
        // 메인 페이지를 보여줍니다
        log.info("메인 페이지로 이동");
        List<goodsVO> vo1 = gService.getListByLikeCount();
        vo1.forEach(item -> log.info("vo1는 " + item.getGname()));
        model.addAttribute("likeGoods", gService.getListByLikeCount());
        
        List<goodsVO> vo2 = gService.getListByInterestOneNotLogin();
        vo2.forEach(item -> log.info("vo2는 " + item.getGname()));
        
        List<goodsVO> vo3 = gService.getListByInterestTwoNotLogin();
        vo3.forEach(item -> log.info("vo3는 " + item.getGname()));
        
        List<goodsVO> vo4 = gService.getListByInterestThreeNotLogin();
        vo4.forEach(item -> log.info("vo4는 " + item.getGname()));
        
        model.addAttribute("interestOneNotLogin", gService.getListByInterestOneNotLogin());
        model.addAttribute("interestTwoNotLogin", gService.getListByInterestTwoNotLogin());
        model.addAttribute("interestThreeNotLogin", gService.getListByInterestThreeNotLogin());
        return "/goodsStore/goodsMain"; // 메인 페이지 JSP의 경로
    }


    @GetMapping("/goodsSearch")
    public String goodsSearch(@RequestParam(value = "searchText", required = false) String searchText, Model model) {
        if (searchText == null || searchText.trim().isEmpty()) {
            return "goodsStore/goodsSearch";
        }

        List<goodsVO> voList = gService.getSearchList(searchText, 0, 10);

        for (goodsVO vo : voList) {
            gCatVO voCat = gService.getCategory(vo.getGno());
            vo.setGcat(voCat);
        }

        model.addAttribute("searchList", voList);
        model.addAttribute("searchText", searchText);

        return "/goodsStore/goodsSearch"; // 이 메서드는 HTML을 반환합니다 (JSP 페이지)
    }

}
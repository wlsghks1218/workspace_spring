package org.hype.controller;

import java.util.List;

import org.hype.domain.SearchRequest;
import org.hype.domain.gCatVO;
import org.hype.domain.goodsVO;
import org.hype.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j;

@Log4j
@RestController
@RequestMapping("/goodsStore")
public class GoodsRestController {
	
    @Autowired
    private GoodsService gService;

    @PostMapping(value = "/loadMoreGoods", consumes = "application/json", produces = "application/json")
    public List<goodsVO> loadMoreGoods(@RequestBody SearchRequest searchRequest) {
        String searchText = searchRequest.getSearchText();
        int page = searchRequest.getPage();

        int offset = (page - 1) * 10;
        List<goodsVO> voList = gService.getSearchList(searchText, offset, 10);

        for (goodsVO vo : voList) {
            gCatVO voCat = gService.getCategory(vo.getGno());
            vo.setGcat(voCat);
        }

        return voList;
    }
}

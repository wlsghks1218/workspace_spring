package org.hype.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hype.domain.gCatVO;
import org.hype.domain.goodsVO;
import org.hype.domain.rankVO;
import org.hype.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class GoodsServiceImpl implements GoodsService{
	
	@Autowired
	private GoodsMapper gMapper;
	
	// 인기 굿즈 8개 불러오기
	@Override
	public List<goodsVO> getListByLikeCount() {
		log.info("인기 굿즈 가져오는 중...");
		return gMapper.getListByLikeCount();
	}

	// 로그인 X 인기 관심사1 굿즈 8개 불러오기
	@Override
	public List<goodsVO> getListByInterestOneNotLogin() {
		List<rankVO> rVo = gMapper.getCategoryRankNotLogin();
		log.info("rVo의 0번 카테고리는 " + rVo.get(0).getCategory());
	    String category = rVo.get(0).getCategory();
	    Map<String, String> params = new HashMap<>();
	    params.put("key", category);
        List<goodsVO> vo = gMapper.getListByInterestNotLogin(params);
        vo.forEach(item -> log.info("rVo 0번의 vo는 " + item.getGname()));
		return gMapper.getListByInterestNotLogin(params);
	}
	
	// 로그인 X 인기 관심사2 굿즈 8개 불러오기
	@Override
	public List<goodsVO> getListByInterestTwoNotLogin() {
		List<rankVO> rVo = gMapper.getCategoryRankNotLogin();
		log.info("rVo의 0번 카테고리는 " + rVo.get(1).getCategory());
	    String category = rVo.get(1).getCategory();
	    Map<String, String> params = new HashMap<>();
	    params.put("key", category);
        List<goodsVO> vo = gMapper.getListByInterestNotLogin(params);
        vo.forEach(item -> log.info("rVo 0번의 vo는 " + item.getGname()));
		return gMapper.getListByInterestNotLogin(params);
	}

	// 로그인 X 인기 관심사3 굿즈 8개 불러오기	
	@Override
	public List<goodsVO> getListByInterestThreeNotLogin() {
		List<rankVO> rVo = gMapper.getCategoryRankNotLogin();
		log.info("rVo의 0번 카테고리는 " + rVo.get(2).getCategory());
	    String category = rVo.get(2).getCategory();
	    Map<String, String> params = new HashMap<>();
	    params.put("key", category);
        List<goodsVO> vo = gMapper.getListByInterestNotLogin(params);
        vo.forEach(item -> log.info("rVo 0번의 vo는 " + item.getGname()));
		return gMapper.getListByInterestNotLogin(params);
	}

	@Override
	public List<goodsVO> getListByInterestOneLogined() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<goodsVO> getListByInterestTwoLogined() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<goodsVO> getListByInterestThreeLogined() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public goodsVO getOneByGno(int gno) {
		return gMapper.getOneByGno(gno);
	}
	
    public List<goodsVO> getSearchList(String searchText, int offset, int limit) {
        return gMapper.getSearchList(searchText, offset, limit);
    }

    public gCatVO getCategory(int gno) {
        return gMapper.getCategory(gno);
    }
}

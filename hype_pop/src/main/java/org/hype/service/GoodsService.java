package org.hype.service;

import java.util.List;

import org.hype.domain.goodsVO;
import org.hype.domain.rankVO;

public interface GoodsService {
	public List<goodsVO> getListByLikeCount();
	public List<goodsVO> getListByInterestOneNotLogin();
	public List<goodsVO> getListByInterestTwoNotLogin();
	public List<goodsVO> getListByInterestThreeNotLogin();
	public List<goodsVO> getListByInterestOneLogined();
	public List<goodsVO> getListByInterestTwoLogined();
	public List<goodsVO> getListByInterestThreeLogined();
	public goodsVO getOneByGno(int gNo);
}

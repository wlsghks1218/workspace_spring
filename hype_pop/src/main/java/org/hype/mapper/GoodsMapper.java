package org.hype.mapper;

import java.util.List;
import java.util.Map;

import org.hype.domain.goodsVO;
import org.hype.domain.rankVO;

public interface GoodsMapper {
	public List<goodsVO> getListByLikeCount();
	public List<rankVO> getCategoryRankNotLogin();
	public List<goodsVO> getListByInterestNotLogin(Map<String, String> map);
	public List<rankVO> getCategoryRankLogined();
	public List<goodsVO> getListByInterestLogined(Map<String, Object> map);
	public goodsVO getOneByGno(int gNo);
}
